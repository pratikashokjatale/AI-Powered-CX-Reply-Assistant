package com.pratikjatale.assistant.ai.controller;

import com.pratikjatale.assistant.ai.dto.request.ApproveReplyRequest;
import com.pratikjatale.assistant.ai.dto.request.GenerateReplyRequest;
import com.pratikjatale.assistant.ai.dto.response.ApiResponse;
import com.pratikjatale.assistant.ai.dto.response.AiInteractionLogDto;
import com.pratikjatale.assistant.ai.dto.response.AiReplySuggestionDto;
import com.pratikjatale.assistant.ai.dto.response.MessageDto;
import com.pratikjatale.assistant.ai.service.AiReplyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
@Tag(name = "AI Reply Assistant", description = "Endpoints for generating AI replies, guardrail evaluations, and agent approvals")
public class AiReplyController {

    private final AiReplyService aiReplyService;

    @PostMapping("/generate-reply")
    @Operation(summary = "Generate AI reply", description = "Retrieves brand knowledge base context, evaluates guardrails, and generates a grounded response suggestion for the agent")
    public ResponseEntity<ApiResponse<AiReplySuggestionDto>> generateReply(@Valid @RequestBody GenerateReplyRequest request) {
        AiReplySuggestionDto suggestion = aiReplyService.generateReply(request);
        return ResponseEntity.ok(ApiResponse.ok("AI reply generated successfully", suggestion));
    }

    @PostMapping("/approve-reply")
    @Operation(summary = "Approve and send reply", description = "Approves (optionally edited) reply, logs the interaction for auditing, and delivers the message to the conversation")
    public ResponseEntity<ApiResponse<MessageDto>> approveReply(@Valid @RequestBody ApproveReplyRequest request) {
        MessageDto approved = aiReplyService.approveReply(request);
        return ResponseEntity.ok(ApiResponse.ok("Reply approved and sent", approved));
    }

    @GetMapping("/logs")
    @Operation(summary = "Get AI interaction logs", description = "Retrieves AI interaction logs, optionally filtered by conversation ID")
    public ResponseEntity<ApiResponse<List<AiInteractionLogDto>>> getInteractionLogs(
            @RequestParam(required = false) Long conversationId) {
        List<AiInteractionLogDto> logs = aiReplyService.getInteractionLogs(conversationId);
        return ResponseEntity.ok(ApiResponse.ok("AI interaction logs retrieved successfully", logs));
    }

    @GetMapping("/logs/{id}")
    @Operation(summary = "Get AI interaction log by ID", description = "Retrieves a single AI interaction log by its ID")
    public ResponseEntity<ApiResponse<AiInteractionLogDto>> getInteractionLogById(@PathVariable Long id) {
        AiInteractionLogDto log = aiReplyService.getInteractionLogById(id);
        return ResponseEntity.ok(ApiResponse.ok("AI interaction log retrieved successfully", log));
    }
}
