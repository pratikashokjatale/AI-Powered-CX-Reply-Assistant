package com.pratikjatale.assistant.ai.controller;

import com.pratikjatale.assistant.ai.dto.response.ApiResponse;
import com.pratikjatale.assistant.ai.dto.response.BrandDto;
import com.pratikjatale.assistant.ai.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@RequiredArgsConstructor
@Tag(name = "Brands", description = "Endpoints for brand management and metadata")
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    @Operation(summary = "Get all brands", description = "Retrieves all supported brands and their tone guidelines")
    public ResponseEntity<ApiResponse<List<BrandDto>>> getAllBrands() {
        return ResponseEntity.ok(ApiResponse.ok(brandService.getAllBrands()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get brand by ID", description = "Retrieves a specific brand by its unique identifier")
    public ResponseEntity<ApiResponse<BrandDto>> getBrandById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(brandService.getBrandById(id)));
    }
}
