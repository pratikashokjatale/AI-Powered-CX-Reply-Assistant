package com.pratikjatale.assistant.ai.dto.response;

import com.pratikjatale.assistant.ai.enums.PolicyCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KnowledgeArticleDto {
    private Long id;
    private Long brandId;
    private String brandName;
    private PolicyCategory category;
    private String title;
    private String content;
    private String keywords;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;
}
