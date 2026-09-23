package com.pratikjatale.assistant.ai.dto.response;

import com.pratikjatale.assistant.ai.enums.GuardrailStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiInteractionLogDto {

    private Long id;
    private Long conversationId;
    private Long brandId;
    private String brandName;
    private String customerMessage;
    private String retrievedContext;
    private String aiGeneratedResponse;
    private String agentEditedResponse;
    private String finalResponse;
    private GuardrailStatus guardrailStatus;
    private String guardrailNotes;
    private String modelUsed;
    private Instant createdAt;
}
