package com.pratikjatale.assistant.ai.repository;

import com.pratikjatale.assistant.ai.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findByCodeIgnoreCase(String code);
    Optional<Brand> findByNameIgnoreCase(String name);
}
