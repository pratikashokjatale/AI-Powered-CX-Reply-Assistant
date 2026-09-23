package com.pratikjatale.assistant.ai.entity;

import com.pratikjatale.assistant.ai.enums.GuardrailStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "ai_interaction_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiInteractionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "customer_message", nullable = false, columnDefinition = "TEXT")
    private String customerMessage;

    @Column(name = "retrieved_context", columnDefinition = "TEXT")
    private String retrievedContext;

    @Column(name = "ai_generated_response", columnDefinition = "TEXT")
    private String aiGeneratedResponse;

    @Column(name = "agent_edited_response", columnDefinition = "TEXT")
    private String agentEditedResponse;

    @Column(name = "final_response", columnDefinition = "TEXT")
    private String finalResponse;

    @Enumerated(EnumType.STRING)
    @Column(name = "guardrail_status", length = 50)
    private GuardrailStatus guardrailStatus;

    @Column(name = "guardrail_notes", columnDefinition = "TEXT")
    private String guardrailNotes;

    @Column(name = "model_used", length = 100)
    private String modelUsed;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
}
