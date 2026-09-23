package com.pratikjatale.assistant.ai.dto.response;

import com.pratikjatale.assistant.ai.enums.SenderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private Long id;
    private Long conversationId;
    private SenderType senderType;
    private String senderName;
    private String content;
    private Instant createdAt;
}
