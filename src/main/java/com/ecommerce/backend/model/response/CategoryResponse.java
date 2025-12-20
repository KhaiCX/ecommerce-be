package com.ecommerce.backend.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CategoryResponse(
        @JsonProperty String name,
        @JsonProperty Boolean deleted) {}
