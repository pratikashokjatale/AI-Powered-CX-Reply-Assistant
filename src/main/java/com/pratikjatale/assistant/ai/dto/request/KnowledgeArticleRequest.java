package com.pratikjatale.assistant.ai.dto.request;

import com.pratikjatale.assistant.ai.enums.PolicyCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KnowledgeArticleRequest {

    @NotNull(message = "Brand ID is required")
    private Long brandId;

    @NotNull(message = "Category is required (RETURN, REFUND, SHIPPING, CANCELLATION, GENERAL)")
    private PolicyCategory category;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Content is required")
    private String content;

    private String keywords;

    @Builder.Default
    private boolean active = true;
}
