package com.pratikjatale.assistant.ai.service.impl;

import com.pratikjatale.assistant.ai.dto.response.BrandDto;
import com.pratikjatale.assistant.ai.entity.Brand;
import com.pratikjatale.assistant.ai.exception.ResourceNotFoundException;
import com.pratikjatale.assistant.ai.repository.BrandRepository;
import com.pratikjatale.assistant.ai.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public List<BrandDto> getAllBrands() {
        return brandRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BrandDto getBrandById(Long id) {
        Brand brand = brandRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Brand not found with ID: " + id));
        return mapToDto(brand);
    }

    private BrandDto mapToDto(Brand brand) {
        return BrandDto.builder()
                .id(brand.getId())
                .name(brand.getName())
                .code(brand.getCode())
                .description(brand.getDescription())
                .toneGuidelines(brand.getToneGuidelines())
                .createdAt(brand.getCreatedAt())
                .build();
    }
}
