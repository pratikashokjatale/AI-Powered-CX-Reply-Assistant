package com.pratikjatale.assistant.ai.service;

import com.pratikjatale.assistant.ai.dto.request.KnowledgeArticleRequest;
import com.pratikjatale.assistant.ai.dto.response.KnowledgeArticleDto;
import com.pratikjatale.assistant.ai.enums.PolicyCategory;

import java.util.List;

public interface KnowledgeBaseService {
    List<KnowledgeArticleDto> getArticlesByBrand(Long brandId);
    List<KnowledgeArticleDto> getArticlesByBrandAndCategory(Long brandId, PolicyCategory category);
    KnowledgeArticleDto getArticleById(Long id);
    KnowledgeArticleDto createArticle(KnowledgeArticleRequest request);
    KnowledgeArticleDto updateArticle(Long id, KnowledgeArticleRequest request);
    void deleteArticle(Long id);
    List<KnowledgeArticleDto> searchArticles(Long brandId, String query);
}
