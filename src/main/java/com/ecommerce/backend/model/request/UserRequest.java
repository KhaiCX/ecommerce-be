package com.ecommerce.backend.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserRequest(
        @JsonProperty("username") String username,
        @JsonProperty("email") String email,
        @JsonProperty("phone") String phone) {}
