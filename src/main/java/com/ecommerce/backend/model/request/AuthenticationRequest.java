package com.ecommerce.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record AuthenticationRequest(
        @NotNull @JsonProperty("email") String email,
        @NotNull @JsonProperty("password") String password) {}
