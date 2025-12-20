package com.ecommerce.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record CategoryRequest(
        @JsonProperty @NotNull String name
) {}
