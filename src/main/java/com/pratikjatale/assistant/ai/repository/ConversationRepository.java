package com.pratikjatale.assistant.ai.repository;

import com.pratikjatale.assistant.ai.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    @Query("SELECT c FROM Conversation c JOIN FETCH c.brand JOIN FETCH c.customer LEFT JOIN FETCH c.order ORDER BY c.updatedAt DESC")
    List<Conversation> findAllWithDetails();

    List<Conversation> findByBrandIdOrderByUpdatedAtDesc(Long brandId);
}
