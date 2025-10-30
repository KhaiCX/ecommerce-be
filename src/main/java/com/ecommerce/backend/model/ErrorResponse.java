package com.ecommerce.backend.model;

import lombok.Builder;
import java.time.LocalDateTime;

@Builder
public record ErrorResponse(
        Integer status,
        String error,
        String message,
        String path,
        LocalDateTime timestamp
) {}
