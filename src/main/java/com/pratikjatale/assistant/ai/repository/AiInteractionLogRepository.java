package com.pratikjatale.assistant.ai.repository;

import com.pratikjatale.assistant.ai.entity.AiInteractionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AiInteractionLogRepository extends JpaRepository<AiInteractionLog, Long> {
    List<AiInteractionLog> findByConversationIdOrderByCreatedAtDesc(Long conversationId);
    List<AiInteractionLog> findAllByOrderByCreatedAtDesc();
}
