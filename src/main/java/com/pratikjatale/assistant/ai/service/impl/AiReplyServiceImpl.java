package com.pratikjatale.assistant.ai.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pratikjatale.assistant.ai.config.AppProperties;
import com.pratikjatale.assistant.ai.dto.request.ApproveReplyRequest;
import com.pratikjatale.assistant.ai.dto.request.GenerateReplyRequest;
import com.pratikjatale.assistant.ai.dto.response.AiInteractionLogDto;
import com.pratikjatale.assistant.ai.dto.response.AiReplySuggestionDto;
import com.pratikjatale.assistant.ai.dto.response.KnowledgeArticleDto;
import com.pratikjatale.assistant.ai.dto.response.MessageDto;
import com.pratikjatale.assistant.ai.entity.*;
import com.pratikjatale.assistant.ai.enums.ConversationStatus;
import com.pratikjatale.assistant.ai.enums.GuardrailStatus;
import com.pratikjatale.assistant.ai.enums.SenderType;
import com.pratikjatale.assistant.ai.exception.ResourceNotFoundException;
import com.pratikjatale.assistant.ai.repository.*;
import com.pratikjatale.assistant.ai.service.AiReplyService;
import com.pratikjatale.assistant.ai.service.GuardrailService;
import com.pratikjatale.assistant.ai.service.GuardrailService.GuardrailResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AiReplyServiceImpl implements AiReplyService {

    private static final Pattern BASE64_IMAGE_PATTERN = Pattern.compile("data:image/[^;]+;base64,[A-Za-z0-9+/=]+");

    private final ConversationRepository conversationRepository;
    private final KnowledgeArticleRepository articleRepository;
    private final MessageRepository messageRepository;
    private final AiInteractionLogRepository aiLogRepository;
    private final GuardrailService guardrailService;
    private final AppProperties appProperties;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @Transactional
    public AiReplySuggestionDto generateReply(GenerateReplyRequest request) {
        Conversation conversation = conversationRepository.findById(request.getConversationId())
                .orElseThrow(() -> new ResourceNotFoundException("Conversation not found with ID: " + request.getConversationId()));

        Brand brand = conversation.getBrand();

        // 1. Identify customer message
        String rawCustomerMessage = request.getCustomerMessage();
        if (rawCustomerMessage == null || rawCustomerMessage.isBlank()) {
            rawCustomerMessage = conversation.getMessages().stream()
                    .filter(m -> m.getSenderType() == SenderType.CUSTOMER)
                    .reduce((first, second) -> second)
                    .map(Message::getContent)
                    .orElse("Hello, I need help with my order.");
        }

        String customerMessage = sanitizeContent(rawCustomerMessage);

        // 2. Retrieve relevant KB information specifically scoped to this brand
        List<KnowledgeArticle> relevantArticles = retrieveBrandKnowledge(brand.getId(), customerMessage);

        // Build retrieved context summary string for agent visibility
        StringBuilder contextBuilder = new StringBuilder();
        for (KnowledgeArticle article : relevantArticles) {
            contextBuilder.append(String.format("[%s - %s]: %s\n\n",
                    article.getCategory(), article.getTitle(), article.getContent()));
        }
        String retrievedContext = contextBuilder.toString().trim();

        // 3. Pre-generation Guardrail check
        GuardrailResult guardrailResult = guardrailService.evaluatePreGeneration(conversation, customerMessage, relevantArticles);

        // 4. Generate AI Suggestion (calling OpenRouter with fallback models, then smart grounded fallback)
        String suggestedReply = callLlmOrFallback(conversation, customerMessage, relevantArticles, guardrailResult, request.getCustomInstruction());

        // 5. Post-generation Guardrail check
        guardrailResult = guardrailService.evaluatePostGeneration(conversation, suggestedReply, guardrailResult);
        if (guardrailResult.getSanitizedSuggestion() != null) {
            suggestedReply = guardrailResult.getSanitizedSuggestion();
        }

        // 6. Persist interaction log (Data & Logging Requirement)
        AiInteractionLog logEntry = AiInteractionLog.builder()
                .conversation(conversation)
                .brand(brand)
                .customerMessage(customerMessage)
                .retrievedContext(retrievedContext)
                .aiGeneratedResponse(suggestedReply)
                .guardrailStatus(guardrailResult.getStatus())
                .guardrailNotes(guardrailResult.getNotes())
                .modelUsed(appProperties.getAi().getModel())
                .createdAt(Instant.now())
                .build();

        AiInteractionLog savedLog = aiLogRepository.save(logEntry);

        List<KnowledgeArticleDto> articleDtos = relevantArticles.stream()
                .map(a -> KnowledgeArticleDto.builder()
                        .id(a.getId())
                        .brandId(brand.getId())
                        .brandName(brand.getName())
                        .category(a.getCategory())
                        .title(a.getTitle())
                        .content(a.getContent())
                        .keywords(a.getKeywords())
                        .active(a.isActive())
                        .build())
                .collect(Collectors.toList());

        return AiReplySuggestionDto.builder()
                .conversationId(conversation.getId())
                .brandId(brand.getId())
                .brandName(brand.getName())
                .customerMessage(customerMessage)
                .suggestedReply(suggestedReply)
                .retrievedContext(retrievedContext)
                .retrievedArticles(articleDtos)
                .guardrailStatus(guardrailResult.getStatus())
                .guardrailNotes(guardrailResult.getNotes())
                .guardrailTriggered(guardrailResult.getStatus() != GuardrailStatus.PASSED)
                .modelUsed(appProperties.getAi().getModel())
                .interactionLogId(savedLog.getId())
                .build();
    }

    @Override
    @Transactional
    public MessageDto approveReply(ApproveReplyRequest request) {
        Conversation conversation = conversationRepository.findById(request.getConversationId())
                .orElseThrow(() -> new ResourceNotFoundException("Conversation not found with ID: " + request.getConversationId()));

        // Update interaction log if provided
        if (request.getInteractionLogId() != null) {
            aiLogRepository.findById(request.getInteractionLogId()).ifPresent(logEntry -> {
                if (request.isEdited()) {
                    logEntry.setAgentEditedResponse(request.getFinalContent());
                }
                logEntry.setFinalResponse(request.getFinalContent());
                aiLogRepository.save(logEntry);
            });
        }

        // Save new message into conversation
        Message message = Message.builder()
                .conversation(conversation)
                .senderType(SenderType.AGENT)
                .senderName(request.getAgentName())
                .content(request.getFinalContent().trim())
                .createdAt(Instant.now())
                .build();

        Message saved = messageRepository.save(message);

        conversation.setStatus(ConversationStatus.OPEN);
        conversation.setUpdatedAt(Instant.now());
        conversationRepository.save(conversation);

        return MessageDto.builder()
                .id(saved.getId())
                .conversationId(conversation.getId())
                .senderType(saved.getSenderType())
                .senderName(saved.getSenderName())
                .content(saved.getContent())
                .createdAt(saved.getCreatedAt())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public List<AiInteractionLogDto> getInteractionLogs(Long conversationId) {
        List<AiInteractionLog> logs;
        if (conversationId != null) {
            logs = aiLogRepository.findByConversationIdOrderByCreatedAtDesc(conversationId);
        } else {
            logs = aiLogRepository.findAllByOrderByCreatedAtDesc();
        }
        return logs.stream().map(this::mapToLogDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AiInteractionLogDto getInteractionLogById(Long id) {
        AiInteractionLog log = aiLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AI interaction log not found with ID: " + id));
        return mapToLogDto(log);
    }

    private AiInteractionLogDto mapToLogDto(AiInteractionLog log) {
        return AiInteractionLogDto.builder()
                .id(log.getId())
                .conversationId(log.getConversation() != null ? log.getConversation().getId() : null)
                .brandId(log.getBrand() != null ? log.getBrand().getId() : null)
                .brandName(log.getBrand() != null ? log.getBrand().getName() : null)
                .customerMessage(log.getCustomerMessage())
                .retrievedContext(log.getRetrievedContext())
                .aiGeneratedResponse(log.getAiGeneratedResponse())
                .agentEditedResponse(log.getAgentEditedResponse())
                .finalResponse(log.getFinalResponse())
                .guardrailStatus(log.getGuardrailStatus())
                .guardrailNotes(log.getGuardrailNotes())
                .modelUsed(log.getModelUsed())
                .createdAt(log.getCreatedAt())
                .build();
    }

    private String sanitizeContent(String content) {
        if (content == null) return "";
        return BASE64_IMAGE_PATTERN.matcher(content).replaceAll("[Attached image: photo proof]");
    }

    private List<KnowledgeArticle> retrieveBrandKnowledge(Long brandId, String query) {
        List<KnowledgeArticle> allBrandArticles = articleRepository.findByBrandIdAndActiveTrue(brandId);
        String lowerQuery = query.toLowerCase();

        // Keyword and semantic keyword relevance ranking
        List<KnowledgeArticle> matched = allBrandArticles.stream()
                .filter(a -> {
                    String title = a.getTitle().toLowerCase();
                    String content = a.getContent().toLowerCase();
                    String keywords = a.getKeywords() != null ? a.getKeywords().toLowerCase() : "";

                    return (lowerQuery.contains("broken") || lowerQuery.contains("photo") || lowerQuery.contains("image")) && (content.contains("broken") || content.contains("damaged"))
                            || lowerQuery.contains("refund") && (title.contains("refund") || content.contains("refund"))
                            || lowerQuery.contains("return") && (title.contains("return") || content.contains("return"))
                            || lowerQuery.contains("ship") && (title.contains("shipping") || content.contains("delivery"))
                            || lowerQuery.contains("cancel") && (title.contains("cancel") || content.contains("cancellation"))
                            || title.contains(lowerQuery)
                            || keywords.contains(lowerQuery);
                })
                .collect(Collectors.toList());

        // If specific keyword matching is empty, fallback to brand's active policies
        return matched.isEmpty() ? allBrandArticles : matched;
    }

    private String callLlmOrFallback(Conversation conversation, String customerMessage,
                                     List<KnowledgeArticle> articles, GuardrailResult guardrails,
                                     String customInstruction) {
        String apiKey = appProperties.getAi().getApiKey();
        if (apiKey != null && !apiKey.isBlank() && !apiKey.equals("mock-key")) {
            // 1. Try configured model
            String primaryModel = appProperties.getAi().getModel();
            try {
                String liveResponse = callOpenRouterApi(conversation, customerMessage, articles, guardrails, customInstruction, primaryModel);
                if (liveResponse != null && !liveResponse.isBlank()) {
                    return liveResponse;
                }
            } catch (Exception e) {
                log.warn("OpenRouter API call with primary model '{}' failed: {}. Attempting fallback to openrouter/auto...", primaryModel, e.getMessage());
            }

            // 2. Try auto routing if primary model failed and was different
            if (!"openrouter/auto".equalsIgnoreCase(primaryModel)) {
                try {
                    String autoResponse = callOpenRouterApi(conversation, customerMessage, articles, guardrails, customInstruction, "openrouter/auto");
                    if (autoResponse != null && !autoResponse.isBlank()) {
                        return autoResponse;
                    }
                } catch (Exception e) {
                    log.warn("OpenRouter fallback 'openrouter/auto' call failed: {}", e.getMessage());
                }
            }
        }
        return generateGroundedResponse(conversation, customerMessage, articles, guardrails, customInstruction);
    }

    private String callOpenRouterApi(Conversation conversation, String customerMessage,
                                     List<KnowledgeArticle> articles, GuardrailResult guardrails,
                                     String customInstruction, String modelToUse) throws Exception {
        String brandName = conversation.getBrand().getName();
        String brandTone = conversation.getBrand().getToneGuidelines();
        String orderInfo = conversation.getOrder() != null
                ? String.format("Order Number: %s, Items: %s, Order Date: %s, Delivery Date: %s, Status: %s",
                conversation.getOrder().getOrderNumber(),
                conversation.getOrder().getItemSummary(),
                conversation.getOrder().getOrderDate(),
                conversation.getOrder().getDeliveryDate(),
                conversation.getOrder().getOrderStatus())
                : "No linked order";

        StringBuilder kbContext = new StringBuilder();
        for (KnowledgeArticle a : articles) {
            kbContext.append("- [").append(a.getCategory()).append("] ").append(a.getTitle()).append(": ").append(a.getContent()).append("\n");
        }

        String systemPrompt = String.format(
                "You are an expert customer support assistant for %s. " +
                "Brand tone guidelines: %s\n" +
                "Order Context: %s\n" +
                "Brand Knowledge Base Policies:\n%s\n" +
                "STRICT GUARDRAILS:\n" +
                "1. Answer ONLY based on the provided brand policies and order context.\n" +
                "2. If customer has already provided the photo proof of damaged item, acknowledge receipt of the photo, verify the policy, and immediately offer replacement or full refund without asking for the photo again.\n" +
                "3. If customer asks for a refund outside policy timeframe, do NOT promise a refund.\n" +
                "4. If knowledge base has no relevant information, politely inform customer and ask for clarification.\n" +
                "5. Be concise, empathetic, and professional.",
                brandName, brandTone, orderInfo, kbContext
        );

        // Build messages array
        ObjectNode requestNode = objectMapper.createObjectNode();
        requestNode.put("model", modelToUse);
        ArrayNode messagesNode = requestNode.putArray("messages");

        ObjectNode systemMsg = messagesNode.addObject();
        systemMsg.put("role", "system");
        systemMsg.put("content", systemPrompt);

        // Include recent conversation messages (up to last 6) for context
        if (conversation.getMessages() != null && !conversation.getMessages().isEmpty()) {
            List<Message> history = conversation.getMessages();
            int startIdx = Math.max(0, history.size() - 6);
            for (int i = startIdx; i < history.size() - 1; i++) {
                Message prev = history.get(i);
                ObjectNode histMsg = messagesNode.addObject();
                histMsg.put("role", prev.getSenderType() == SenderType.CUSTOMER ? "user" : "assistant");
                histMsg.put("content", sanitizeContent(prev.getContent()));
            }
        }

        // Current customer message + custom instruction
        String userPrompt = "Customer says: \"" + customerMessage + "\"";
        if (customInstruction != null && !customInstruction.isBlank()) {
            userPrompt += "\nAgent instruction: " + customInstruction;
        }

        ObjectNode currentMsg = messagesNode.addObject();
        currentMsg.put("role", "user");
        currentMsg.put("content", userPrompt);

        requestNode.put("temperature", appProperties.getAi().getTemperature());
        requestNode.put("max_tokens", appProperties.getAi().getMaxTokens());

        String jsonPayload = objectMapper.writeValueAsString(requestNode);

        java.net.http.HttpClient client = java.net.http.HttpClient.newBuilder()
                .connectTimeout(java.time.Duration.ofSeconds(10))
                .build();

        java.net.http.HttpRequest httpRequest = java.net.http.HttpRequest.newBuilder()
                .uri(java.net.URI.create(appProperties.getAi().getBaseUrl() + "/chat/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + appProperties.getAi().getApiKey())
                .header("HTTP-Referer", "http://localhost:8080")
                .header("X-Title", "AI CX Reply Assistant")
                .timeout(java.time.Duration.ofSeconds(15))
                .POST(java.net.http.HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();

        java.net.http.HttpResponse<String> response = client.send(httpRequest, java.net.http.HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            JsonNode root = objectMapper.readTree(response.body());
            JsonNode choices = root.path("choices");
            if (choices.isArray() && !choices.isEmpty()) {
                String content = choices.get(0).path("message").path("content").asText();
                if (content != null && !content.isBlank()) {
                    return content.trim();
                }
            }
        } else {
            log.warn("OpenRouter API returned HTTP {}: {}", response.statusCode(), response.body());
        }
        return null;
    }

    private String generateGroundedResponse(Conversation conversation, String customerMessage,
                                            List<KnowledgeArticle> articles, GuardrailResult guardrails,
                                            String customInstruction) {
        String brandName = conversation.getBrand().getName();
        String brandCode = conversation.getBrand().getCode();
        String customerName = conversation.getCustomer().getName();
        String orderNumber = conversation.getOrder() != null ? conversation.getOrder().getOrderNumber() : "your order";
        String item = conversation.getOrder() != null ? conversation.getOrder().getItemSummary() : "your item";
        String lowerMsg = customerMessage.toLowerCase();

        if (guardrails.isMissingContext()) {
            return String.format("Hi %s, thank you for reaching out to %s support. I would be happy to help you with %s (%s). Could you please provide a few more details so I can look into this for you right away?",
                    customerName, brandName, orderNumber, item);
        }

        if (guardrails.getStatus() == GuardrailStatus.WARNING && guardrails.isPolicyViolationRisk()) {
            return String.format("Hi %s, thank you for contacting %s. We are sorry to hear about your experience. Based on our policy, refund requests must be initiated within our specified policy window from the delivery date. As this timeframe has passed for %s, we are unable to process a direct refund. However, please let us know if there is anything else we can do to assist you.",
                    customerName, brandName, orderNumber);
        }

        boolean hasImageOrPhoto = lowerMsg.contains("[attached image") || lowerMsg.contains("photo") || lowerMsg.contains("image") || lowerMsg.contains("picture");
        boolean isBrokenOrDamaged = lowerMsg.contains("broken") || lowerMsg.contains("damage") || lowerMsg.contains("defect") || lowerMsg.contains("leaky");

        if ("AURA".equalsIgnoreCase(brandCode)) {
            if (hasImageOrPhoto) {
                return String.format("Hi %s, thank you for providing the photo of your damaged %s! Under Aura Skincare's damaged item policy, you are fully covered and no return shipment of the broken glass is required. We would be delighted to arrange an immediate free replacement or process a 100%% full refund. Please let us know which option you would prefer!",
                        customerName, item);
            } else if (isBrokenOrDamaged) {
                return String.format("Hi %s, I am so sorry to hear that your bottle of %s arrived broken! Please don't worry—under Aura Skincare's damaged item policy, you are fully covered. Please reply with a quick photo of the broken bottle and outer package. Once received, we will gladly arrange an immediate free replacement or issue a 100%% full refund without requiring you to return the broken glass. We apologize for the inconvenience!",
                        customerName, item);
            } else if (lowerMsg.contains("ship") || lowerMsg.contains("delivery") || lowerMsg.contains("arrive")) {
                return String.format("Hi %s, thank you for checking on your shipment for %s. Standard shipping typically takes 3-5 business days to arrive. Please let us know if you need tracking updates or further assistance!",
                        customerName, brandName);
            } else if (lowerMsg.contains("return") || lowerMsg.contains("refund") || lowerMsg.contains("rma")) {
                return String.format("Hi %s, for returns under %s's 7-day policy, unopened and unused items can be returned within 7 calendar days of delivery. For damaged items, please send a photo for an immediate resolution. Let us know how we can best assist you!",
                        customerName, brandName);
            } else {
                return String.format("Hi %s, thank you for contacting %s support regarding %s (%s). How can we assist you today?",
                        customerName, brandName, orderNumber, item);
            }
        } else {
            // Brand B (e.g. Apex Tech)
            if (hasImageOrPhoto || isBrokenOrDamaged) {
                return String.format("Hello %s, thank you for contacting %s support. We apologize that your %s arrived damaged. Under our warranty and return policy, we will provide a prepaid shipping label for you to return the item for diagnostic inspection, after which we will issue a replacement or refund. Please let us know if you would like us to generate the return label now.",
                        customerName, brandName, item);
            } else {
                return String.format("Hello %s, thank you for contacting %s support regarding %s (%s). How can we assist you today?",
                        customerName, brandName, orderNumber, item);
            }
        }
    }
}
