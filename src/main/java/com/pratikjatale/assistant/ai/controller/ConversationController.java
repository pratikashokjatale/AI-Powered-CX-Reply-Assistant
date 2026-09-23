package com.pratikjatale.assistant.ai.controller;

import com.pratikjatale.assistant.ai.dto.request.SendMessageRequest;
import com.pratikjatale.assistant.ai.dto.response.ApiResponse;
import com.pratikjatale.assistant.ai.dto.response.ConversationDto;
import com.pratikjatale.assistant.ai.dto.response.MessageDto;
import com.pratikjatale.assistant.ai.service.ConversationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conversations")
@RequiredArgsConstructor
@Tag(name = "Conversations", description = "Endpoints for customer-agent conversations, history, and message exchange")
public class ConversationController {

    private final ConversationService conversationService;

    @GetMapping
    @Operation(summary = "Get all conversations", description = "Retrieves all customer conversations with details and order context")
    public ResponseEntity<ApiResponse<List<ConversationDto>>> getAllConversations() {
        return ResponseEntity.ok(ApiResponse.ok(conversationService.getAllConversations()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get conversation by ID", description = "Retrieves a single conversation with full message history and order context")
    public ResponseEntity<ApiResponse<ConversationDto>> getConversationById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(conversationService.getConversationById(id)));
    }

    @PostMapping("/{id}/messages")
    @Operation(summary = "Send a message", description = "Send a message into the conversation (supports both Customer and Agent side for live back-and-forth testing)")
    public ResponseEntity<ApiResponse<MessageDto>> sendMessage(
            @PathVariable Long id,
            @Valid @RequestBody SendMessageRequest request) {
        MessageDto sent = conversationService.sendMessage(id, request);
        return ResponseEntity.ok(ApiResponse.ok("Message sent successfully", sent));
    }

    @PostMapping("/{id}/reset-scenario")
    @Operation(summary = "Reset to assessment scenario", description = "Resets conversation back to the default assessment test scenario: 'My order was delivered but the bottle is broken. What can I do?'")
    public ResponseEntity<ApiResponse<Void>> resetScenario(@PathVariable Long id) {
        conversationService.resetToInitialScenario(id);
        return ResponseEntity.ok(ApiResponse.ok("Scenario reset successfully", null));
    }
}
