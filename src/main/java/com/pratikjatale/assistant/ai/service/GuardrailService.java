package com.pratikjatale.assistant.ai.service;

import com.pratikjatale.assistant.ai.entity.Conversation;
import com.pratikjatale.assistant.ai.entity.KnowledgeArticle;
import com.pratikjatale.assistant.ai.enums.GuardrailStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public interface GuardrailService {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class GuardrailResult {
        private GuardrailStatus status;
        private String notes;
        private boolean policyViolationRisk;
        private boolean missingContext;
        private String sanitizedSuggestion;
    }

    GuardrailResult evaluatePreGeneration(Conversation conversation, String customerMessage, List<KnowledgeArticle> retrievedArticles);

    GuardrailResult evaluatePostGeneration(Conversation conversation, String generatedReply, GuardrailResult preEval);
}
