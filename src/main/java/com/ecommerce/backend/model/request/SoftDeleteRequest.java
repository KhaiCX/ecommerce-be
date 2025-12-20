package com.ecommerce.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SoftDeleteRequest(
        @JsonProperty("is_soft_delete") Boolean isSoftDelete) {}
