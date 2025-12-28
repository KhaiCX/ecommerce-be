package com.ecommerce.backend.service;

import com.ecommerce.backend.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductSkuService {
    private final ProductVariantRepository productSkuRepository;
}
