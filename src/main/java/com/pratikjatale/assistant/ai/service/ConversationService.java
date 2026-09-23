package com.pratikjatale.assistant.ai.service;

import com.pratikjatale.assistant.ai.dto.request.SendMessageRequest;
import com.pratikjatale.assistant.ai.dto.response.ConversationDto;
import com.pratikjatale.assistant.ai.dto.response.MessageDto;

import java.util.List;

public interface ConversationService {
    List<ConversationDto> getAllConversations();
    ConversationDto getConversationById(Long id);
    MessageDto sendMessage(Long conversationId, SendMessageRequest request);
    void resetToInitialScenario(Long conversationId);
}
