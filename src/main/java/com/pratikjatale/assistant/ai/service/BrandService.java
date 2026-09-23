package com.pratikjatale.assistant.ai.service;

import com.pratikjatale.assistant.ai.dto.response.BrandDto;

import java.util.List;

public interface BrandService {
    List<BrandDto> getAllBrands();
    BrandDto getBrandById(Long id);
}
