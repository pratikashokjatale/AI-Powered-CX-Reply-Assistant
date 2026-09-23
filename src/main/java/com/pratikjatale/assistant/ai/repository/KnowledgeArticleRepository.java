package com.pratikjatale.assistant.ai.repository;

import com.pratikjatale.assistant.ai.entity.KnowledgeArticle;
import com.pratikjatale.assistant.ai.enums.PolicyCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KnowledgeArticleRepository extends JpaRepository<KnowledgeArticle, Long> {

    List<KnowledgeArticle> findByBrandIdAndActiveTrue(Long brandId);

    List<KnowledgeArticle> findByBrandIdAndCategoryAndActiveTrue(Long brandId, PolicyCategory category);

    @Query("SELECT ka FROM KnowledgeArticle ka WHERE ka.brand.id = :brandId AND ka.active = true AND " +
           "(LOWER(ka.title) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(ka.content) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(ka.keywords) LIKE LOWER(CONCAT('%', :query, '%')))")
    List<KnowledgeArticle> searchByBrandAndQuery(@Param("brandId") Long brandId, @Param("query") String query);
}
