package com.ecommerce.backend.model.response;

import com.ecommerce.backend.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record ProductDetailResponse(
        @JsonProperty("product") Product product) {}
