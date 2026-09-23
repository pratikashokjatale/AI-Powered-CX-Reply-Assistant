package com.pratikjatale.assistant.ai.service;

import com.pratikjatale.assistant.ai.dto.request.ApproveReplyRequest;
import com.pratikjatale.assistant.ai.dto.request.GenerateReplyRequest;
import com.pratikjatale.assistant.ai.dto.response.AiInteractionLogDto;
import com.pratikjatale.assistant.ai.dto.response.AiReplySuggestionDto;
import com.pratikjatale.assistant.ai.dto.response.MessageDto;

import java.util.List;

public interface AiReplyService {
    AiReplySuggestionDto generateReply(GenerateReplyRequest request);
    MessageDto approveReply(ApproveReplyRequest request);
    List<AiInteractionLogDto> getInteractionLogs(Long conversationId);
    AiInteractionLogDto getInteractionLogById(Long id);
}
