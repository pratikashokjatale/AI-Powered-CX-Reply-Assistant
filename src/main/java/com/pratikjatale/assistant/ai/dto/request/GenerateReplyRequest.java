package com.pratikjatale.assistant.ai.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenerateReplyRequest {

    @NotNull(message = "Conversation ID is required")
    private Long conversationId;

    // Optional override or custom prompt instructions from the agent (e.g. "regenerate more politely")
    private String customInstruction;

    // Optional message to generate reply for; if null, defaults to latest customer message
    private String customerMessage;
}
