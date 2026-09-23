package com.pratikjatale.assistant.ai.service.impl;

import com.pratikjatale.assistant.ai.dto.request.KnowledgeArticleRequest;
import com.pratikjatale.assistant.ai.dto.response.KnowledgeArticleDto;
import com.pratikjatale.assistant.ai.entity.Brand;
import com.pratikjatale.assistant.ai.entity.KnowledgeArticle;
import com.pratikjatale.assistant.ai.enums.PolicyCategory;
import com.pratikjatale.assistant.ai.exception.ResourceNotFoundException;
import com.pratikjatale.assistant.ai.repository.BrandRepository;
import com.pratikjatale.assistant.ai.repository.KnowledgeArticleRepository;
import com.pratikjatale.assistant.ai.service.KnowledgeBaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class KnowledgeBaseServiceImpl implements KnowledgeBaseService {

    private final KnowledgeArticleRepository articleRepository;
    private final BrandRepository brandRepository;

    @Override
    public List<KnowledgeArticleDto> getArticlesByBrand(Long brandId) {
        return articleRepository.findByBrandIdAndActiveTrue(brandId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<KnowledgeArticleDto> getArticlesByBrandAndCategory(Long brandId, PolicyCategory category) {
        return articleRepository.findByBrandIdAndCategoryAndActiveTrue(brandId, category).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public KnowledgeArticleDto getArticleById(Long id) {
        KnowledgeArticle article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Knowledge article not found with ID: " + id));
        return mapToDto(article);
    }

    @Override
    @Transactional
    public KnowledgeArticleDto createArticle(KnowledgeArticleRequest request) {
        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with ID: " + request.getBrandId()));

        KnowledgeArticle article = KnowledgeArticle.builder()
                .brand(brand)
                .category(request.getCategory())
                .title(request.getTitle())
                .content(request.getContent())
                .keywords(request.getKeywords())
                .active(request.isActive())
                .build();

        KnowledgeArticle saved = articleRepository.save(article);
        log.info("Created new knowledge article ID: {} for brand: {}", saved.getId(), brand.getName());
        return mapToDto(saved);
    }

    @Override
    @Transactional
    public KnowledgeArticleDto updateArticle(Long id, KnowledgeArticleRequest request) {
        KnowledgeArticle article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Knowledge article not found with ID: " + id));

        article.setCategory(request.getCategory());
        article.setTitle(request.getTitle());
        article.setContent(request.getContent());
        article.setKeywords(request.getKeywords());
        article.setActive(request.isActive());

        KnowledgeArticle updated = articleRepository.save(article);
        log.info("Updated knowledge article ID: {}", updated.getId());
        return mapToDto(updated);
    }

    @Override
    @Transactional
    public void deleteArticle(Long id) {
        KnowledgeArticle article = articleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Knowledge article not found with ID: " + id));
        articleRepository.delete(article);
        log.info("Deleted knowledge article ID: {}", id);
    }

    @Override
    public List<KnowledgeArticleDto> searchArticles(Long brandId, String query) {
        if (query == null || query.isBlank()) {
            return getArticlesByBrand(brandId);
        }
        return articleRepository.searchByBrandAndQuery(brandId, query.trim()).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private KnowledgeArticleDto mapToDto(KnowledgeArticle article) {
        return KnowledgeArticleDto.builder()
                .id(article.getId())
                .brandId(article.getBrand().getId())
                .brandName(article.getBrand().getName())
                .category(article.getCategory())
                .title(article.getTitle())
                .content(article.getContent())
                .keywords(article.getKeywords())
                .active(article.isActive())
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .build();
    }
}
