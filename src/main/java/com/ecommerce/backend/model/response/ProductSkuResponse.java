package com.ecommerce.backend.model.response;

import com.ecommerce.backend.entity.ProductVariant;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import java.util.List;

@Builder
public record ProductSkuResponse(
        @JsonProperty("product_skus") List<ProductVariant> productSkus) {}
