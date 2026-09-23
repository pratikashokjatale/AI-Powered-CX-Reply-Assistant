package com.pratikjatale.assistant.ai.dto.response;

import com.pratikjatale.assistant.ai.enums.ConversationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConversationDto {
    private Long id;
    private Long brandId;
    private String brandName;
    private String brandCode;
    private Long customerId;
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private OrderDto order;
    private String channel;
    private ConversationStatus status;
    private String latestCustomerMessage;
    @Builder.Default
    private List<MessageDto> messages = new ArrayList<>();
    private Instant createdAt;
    private Instant updatedAt;
}
