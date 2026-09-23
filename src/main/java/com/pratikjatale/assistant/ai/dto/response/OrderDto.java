package com.pratikjatale.assistant.ai.dto.response;

import com.pratikjatale.assistant.ai.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String orderNumber;
    private String customerName;
    private String brandName;
    private String itemSummary;
    private OrderStatus orderStatus;
    private Instant orderDate;
    private Instant deliveryDate;
    private BigDecimal totalAmount;
    private String currency;
}
