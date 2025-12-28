package com.ecommerce.backend.model.response;

import com.ecommerce.backend.entity.Product;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import java.util.List;

@Builder
public record ProductResponse(
        @JsonProperty("products") List<Product> products,
        @JsonProperty("page") PageResponse pageResponse) {}
