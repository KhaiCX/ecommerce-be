package com.ecommerce.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record RefreshTokenRequest(
        @NotNull @JsonProperty("refresh_token") String refreshToken) {}
