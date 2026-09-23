package com.pratikjatale.assistant.ai.service.impl;

import com.pratikjatale.assistant.ai.entity.Conversation;
import com.pratikjatale.assistant.ai.entity.KnowledgeArticle;
import com.pratikjatale.assistant.ai.entity.Order;
import com.pratikjatale.assistant.ai.enums.GuardrailStatus;
import com.pratikjatale.assistant.ai.service.GuardrailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class GuardrailServiceImpl implements GuardrailService {

    // Regex to detect if customer message specifies days elapsed (e.g., "received this 20 days ago")
    private static final Pattern DAYS_AGO_PATTERN = Pattern.compile("(\\d+)\\s*(?:days?|weeks?)\\s*(?:ago|past|back)?", Pattern.CASE_INSENSITIVE);

    @Override
    public GuardrailResult evaluatePreGeneration(Conversation conversation, String customerMessage, List<KnowledgeArticle> retrievedArticles) {
        // Case 1: No knowledge articles retrieved at all
        if (retrievedArticles == null || retrievedArticles.isEmpty()) {
            return GuardrailResult.builder()
                    .status(GuardrailStatus.MISSING_CONTEXT)
                    .missingContext(true)
                    .policyViolationRisk(false)
                    .notes("GUARDRAIL TRIGGERED: No matching brand policy found in the Knowledge Base. The AI should not make policy commitments; ask the customer for more details or escalate to a senior specialist.")
                    .build();
        }

        // Case 2: Temporal violation check (Assessment Scenario: "I received this 20 days ago. Can I get a refund?" vs 7-day policy)
        String lowerMsg = customerMessage.toLowerCase();
        boolean askingForRefund = lowerMsg.contains("refund") || lowerMsg.contains("money back") || lowerMsg.contains("return");

        if (askingForRefund) {
            long daysElapsed = extractDaysElapsed(customerMessage, conversation.getOrder());
            long maxRefundWindowDays = extractRefundWindowDays(retrievedArticles);

            if (daysElapsed > maxRefundWindowDays && maxRefundWindowDays > 0) {
                return GuardrailResult.builder()
                        .status(GuardrailStatus.WARNING)
                        .policyViolationRisk(true)
                        .missingContext(false)
                        .notes(String.format("GUARDRAIL WARNING: Customer is inquiring about refund/return, but order delivery / stated elapsed time (%d days) exceeds brand's %d-day refund policy window. AI is instructed NOT to promise a refund.", daysElapsed, maxRefundWindowDays))
                        .build();
            }
        }

        return GuardrailResult.builder()
                .status(GuardrailStatus.PASSED)
                .policyViolationRisk(false)
                .missingContext(false)
                .notes("Guardrail checks passed: Grounded in active brand knowledge base.")
                .build();
    }

    @Override
    public GuardrailResult evaluatePostGeneration(Conversation conversation, String generatedReply, GuardrailResult preEval) {
        String lowerReply = generatedReply.toLowerCase();

        // If pre-eval flagged a policy violation risk, make sure generated response didn't promise refund
        if (preEval.isPolicyViolationRisk()) {
            if (lowerReply.contains("we will issue a full refund") || lowerReply.contains("i have processed your refund") || lowerReply.contains("you are eligible for a refund")) {
                log.warn("Post-generation guardrail blocked: Model made ungrounded refund promise despite out-of-window warning");
                return GuardrailResult.builder()
                        .status(GuardrailStatus.BLOCKED)
                        .policyViolationRisk(true)
                        .missingContext(false)
                        .notes("GUARDRAIL BLOCKED: Model generated an unauthorized refund promise exceeding policy window. Auto-sanitized to respectful policy clarification.")
                        .sanitizedSuggestion("Thank you for reaching out to us. According to our policy, refund requests must be initiated within the permitted policy window from delivery. Because the delivery occurred outside this timeframe, we are unable to issue a direct refund. However, please let us know how else we can assist you with your order.")
                        .build();
            }
        }

        return preEval;
    }

    private long extractDaysElapsed(String customerMessage, Order order) {
        Matcher matcher = DAYS_AGO_PATTERN.matcher(customerMessage);
        if (matcher.find()) {
            int num = Integer.parseInt(matcher.group(1));
            if (customerMessage.toLowerCase().contains("week")) {
                return num * 7L;
            }
            return num;
        }

        // Fallback to order delivery date if available
        if (order != null && order.getDeliveryDate() != null) {
            return Math.max(0, Duration.between(order.getDeliveryDate(), Instant.now()).toDays());
        }

        return 0;
    }

    private long extractRefundWindowDays(List<KnowledgeArticle> articles) {
        for (KnowledgeArticle article : articles) {
            String combined = (article.getTitle() + " " + article.getContent()).toLowerCase();
            if (combined.contains("7 days") || combined.contains("7 calendar days")) {
                return 7;
            }
            if (combined.contains("30 days") || combined.contains("30 calendar days")) {
                return 30;
            }
            if (combined.contains("14 days")) {
                return 14;
            }
        }
        return 7; // default standard
    }
}
