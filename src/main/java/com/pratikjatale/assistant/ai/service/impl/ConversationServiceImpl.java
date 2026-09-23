package com.pratikjatale.assistant.ai.service.impl;

import com.pratikjatale.assistant.ai.dto.request.SendMessageRequest;
import com.pratikjatale.assistant.ai.dto.response.ConversationDto;
import com.pratikjatale.assistant.ai.dto.response.MessageDto;
import com.pratikjatale.assistant.ai.dto.response.OrderDto;
import com.pratikjatale.assistant.ai.entity.Conversation;
import com.pratikjatale.assistant.ai.entity.Message;
import com.pratikjatale.assistant.ai.entity.Order;
import com.pratikjatale.assistant.ai.enums.ConversationStatus;
import com.pratikjatale.assistant.ai.enums.SenderType;
import com.pratikjatale.assistant.ai.exception.ResourceNotFoundException;
import com.pratikjatale.assistant.ai.repository.ConversationRepository;
import com.pratikjatale.assistant.ai.repository.MessageRepository;
import com.pratikjatale.assistant.ai.service.ConversationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ConversationServiceImpl implements ConversationService {

    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;

    @Override
    public List<ConversationDto> getAllConversations() {
        return conversationRepository.findAllWithDetails().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ConversationDto getConversationById(Long id) {
        Conversation conversation = conversationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conversation not found with ID: " + id));
        return mapToDto(conversation);
    }

    @Override
    @Transactional
    public MessageDto sendMessage(Long conversationId, SendMessageRequest request) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ResourceNotFoundException("Conversation not found with ID: " + conversationId));

        String senderName = request.getSenderName();
        if (senderName == null || senderName.isBlank()) {
            senderName = request.getSenderType() == SenderType.CUSTOMER
                    ? conversation.getCustomer().getName()
                    : "Support Agent";
        }

        Message message = Message.builder()
                .conversation(conversation)
                .senderType(request.getSenderType())
                .senderName(senderName)
                .content(request.getContent().trim())
                .createdAt(Instant.now())
                .build();

        Message saved = messageRepository.save(message);

        // Update conversation state
        if (request.getSenderType() == SenderType.CUSTOMER) {
            conversation.setStatus(ConversationStatus.WAITING_AGENT);
        } else {
            conversation.setStatus(ConversationStatus.OPEN);
        }
        conversation.setUpdatedAt(Instant.now());
        conversationRepository.save(conversation);

        log.info("Message sent in conversation ID: {} by {}", conversationId, request.getSenderType());
        return mapMessageToDto(saved);
    }

    @Override
    @Transactional
    public void resetToInitialScenario(Long conversationId) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ResourceNotFoundException("Conversation not found with ID: " + conversationId));

        // Delete existing messages and add initial scenario message
        messageRepository.deleteAll(conversation.getMessages());
        conversation.getMessages().clear();

        Message initialMsg = Message.builder()
                .conversation(conversation)
                .senderType(SenderType.CUSTOMER)
                .senderName(conversation.getCustomer().getName())
                .content("My order was delivered but the bottle is broken. What can I do?")
                .createdAt(Instant.now())
                .build();
        messageRepository.save(initialMsg);

        conversation.setStatus(ConversationStatus.WAITING_AGENT);
        conversation.setUpdatedAt(Instant.now());
        conversationRepository.save(conversation);
        log.info("Reset conversation ID: {} to assessment scenario", conversationId);
    }

    private ConversationDto mapToDto(Conversation conversation) {
        List<MessageDto> messageDtos = conversation.getMessages().stream()
                .map(this::mapMessageToDto)
                .collect(Collectors.toList());

        String latestCustomerMessage = conversation.getMessages().stream()
                .filter(m -> m.getSenderType() == SenderType.CUSTOMER)
                .reduce((first, second) -> second)
                .map(Message::getContent)
                .orElse("");

        OrderDto orderDto = null;
        if (conversation.getOrder() != null) {
            Order o = conversation.getOrder();
            orderDto = OrderDto.builder()
                    .id(o.getId())
                    .orderNumber(o.getOrderNumber())
                    .customerName(o.getCustomer().getName())
                    .brandName(o.getBrand().getName())
                    .itemSummary(o.getItemSummary())
                    .orderStatus(o.getOrderStatus())
                    .orderDate(o.getOrderDate())
                    .deliveryDate(o.getDeliveryDate())
                    .totalAmount(o.getTotalAmount())
                    .currency(o.getCurrency())
                    .build();
        }

        return ConversationDto.builder()
                .id(conversation.getId())
                .brandId(conversation.getBrand().getId())
                .brandName(conversation.getBrand().getName())
                .brandCode(conversation.getBrand().getCode())
                .customerId(conversation.getCustomer().getId())
                .customerName(conversation.getCustomer().getName())
                .customerEmail(conversation.getCustomer().getEmail())
                .customerPhone(conversation.getCustomer().getPhone())
                .order(orderDto)
                .channel(conversation.getChannel())
                .status(conversation.getStatus())
                .latestCustomerMessage(latestCustomerMessage)
                .messages(messageDtos)
                .createdAt(conversation.getCreatedAt())
                .updatedAt(conversation.getUpdatedAt())
                .build();
    }

    private MessageDto mapMessageToDto(Message message) {
        return MessageDto.builder()
                .id(message.getId())
                .conversationId(message.getConversation().getId())
                .senderType(message.getSenderType())
                .senderName(message.getSenderName())
                .content(message.getContent())
                .createdAt(message.getCreatedAt())
                .build();
    }
}
