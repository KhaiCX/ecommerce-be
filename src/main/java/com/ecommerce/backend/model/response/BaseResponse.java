package com.ecommerce.backend.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BaseResponse(
        @JsonProperty("message_response") String message) {}
