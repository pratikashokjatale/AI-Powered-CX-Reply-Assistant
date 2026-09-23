package com.pratikjatale.assistant.ai.controller;

import com.pratikjatale.assistant.ai.dto.request.KnowledgeArticleRequest;
import com.pratikjatale.assistant.ai.dto.response.ApiResponse;
import com.pratikjatale.assistant.ai.dto.response.KnowledgeArticleDto;
import com.pratikjatale.assistant.ai.enums.PolicyCategory;
import com.pratikjatale.assistant.ai.service.KnowledgeBaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/knowledge-base")
@RequiredArgsConstructor
@Tag(name = "Knowledge Base", description = "Endpoints for managing brand policies, guidelines, and article CRUD")
public class KnowledgeBaseController {

    private final KnowledgeBaseService knowledgeBaseService;

    @GetMapping("/brand/{brandId}")
    @Operation(summary = "Get brand knowledge base", description = "Retrieves all active policy articles for a specific brand")
    public ResponseEntity<ApiResponse<List<KnowledgeArticleDto>>> getArticlesByBrand(
            @PathVariable Long brandId,
            @RequestParam(required = false) PolicyCategory category) {
        if (category != null) {
            return ResponseEntity.ok(ApiResponse.ok(knowledgeBaseService.getArticlesByBrandAndCategory(brandId, category)));
        }
        return ResponseEntity.ok(ApiResponse.ok(knowledgeBaseService.getArticlesByBrand(brandId)));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get article by ID", description = "Retrieves a single knowledge article by its ID")
    public ResponseEntity<ApiResponse<KnowledgeArticleDto>> getArticleById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(knowledgeBaseService.getArticleById(id)));
    }

    @GetMapping("/brand/{brandId}/search")
    @Operation(summary = "Search brand knowledge", description = "Searches policy articles by keyword or phrase for a specific brand")
    public ResponseEntity<ApiResponse<List<KnowledgeArticleDto>>> searchArticles(
            @PathVariable Long brandId,
            @RequestParam String query) {
        return ResponseEntity.ok(ApiResponse.ok(knowledgeBaseService.searchArticles(brandId, query)));
    }

    @PostMapping
    @Operation(summary = "Create knowledge article", description = "Creates a new policy or article for a brand")
    public ResponseEntity<ApiResponse<KnowledgeArticleDto>> createArticle(@Valid @RequestBody KnowledgeArticleRequest request) {
        KnowledgeArticleDto created = knowledgeBaseService.createArticle(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.ok("Article created successfully", created));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update knowledge article", description = "Updates an existing policy article for a brand")
    public ResponseEntity<ApiResponse<KnowledgeArticleDto>> updateArticle(
            @PathVariable Long id,
            @Valid @RequestBody KnowledgeArticleRequest request) {
        KnowledgeArticleDto updated = knowledgeBaseService.updateArticle(id, request);
        return ResponseEntity.ok(ApiResponse.ok("Article updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete knowledge article", description = "Deletes a policy article")
    public ResponseEntity<ApiResponse<Void>> deleteArticle(@PathVariable Long id) {
        knowledgeBaseService.deleteArticle(id);
        return ResponseEntity.ok(ApiResponse.ok("Article deleted successfully", null));
    }
}
