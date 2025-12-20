package com.ecommerce.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record ProductRequest(
        @JsonProperty("name") @NotNull String name
) {}
