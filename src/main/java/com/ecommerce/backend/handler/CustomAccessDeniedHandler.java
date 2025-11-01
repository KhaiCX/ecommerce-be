package com.ecommerce.backend.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json;charset=UTF-8");

        String json = String.format(
                "{\"status\": \"%s\"," +
                        "\"error\": \"ACCESS_DENIED\"," +
                        "\"message\": \"%s\"," +
                        "\"path\": \"%s\"," +
                        "\"timestamp\": \"%s\"}",
                HttpStatus.FORBIDDEN,
                accessDeniedException.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );

        response.getWriter().write(json);
    }
}
