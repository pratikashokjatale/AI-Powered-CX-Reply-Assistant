package com.pratikjatale.assistant.ai.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApproveReplyRequest {

    @NotNull(message = "Conversation ID is required")
    private Long conversationId;

    private Long interactionLogId;

    @NotBlank(message = "Final response text is required")
    private String finalContent;

    // Agent name
    @Builder.Default
    private String agentName = "Support Agent";

    // Was the response edited by the agent from original AI suggestion?
    private boolean edited;
}
