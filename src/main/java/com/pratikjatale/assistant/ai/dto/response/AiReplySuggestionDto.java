package com.pratikjatale.assistant.ai.dto.response;

import com.pratikjatale.assistant.ai.enums.GuardrailStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiReplySuggestionDto {
    private Long conversationId;
    private Long brandId;
    private String brandName;
    private String customerMessage;
    private String suggestedReply;
    private String retrievedContext;
    @Builder.Default
    private List<KnowledgeArticleDto> retrievedArticles = new ArrayList<>();
    private GuardrailStatus guardrailStatus;
    private String guardrailNotes;
    private boolean guardrailTriggered;
    private String modelUsed;
    private Long interactionLogId;
}
