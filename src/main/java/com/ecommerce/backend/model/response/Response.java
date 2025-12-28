package com.ecommerce.backend.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Response(
        @JsonProperty("message_response") String message) {}
