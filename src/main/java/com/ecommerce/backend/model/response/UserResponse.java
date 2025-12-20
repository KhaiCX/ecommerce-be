package com.ecommerce.backend.model.response;

import com.ecommerce.backend.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import java.util.List;

@Builder
public record UserResponse(
        @JsonProperty("users") List<User> users,
        @JsonProperty("pageNum") int pageNum,
        @JsonProperty("pageSize") int pageSize,
        @JsonProperty("currentPage") int currentPage,
        @JsonProperty("totalPages") int totalPages,
        @JsonProperty("totalItems") long totalItems,
        @JsonProperty("startPage") int startPage,
        @JsonProperty("endPage") int endPage,
        @JsonProperty("sortField") String sortField,
        @JsonProperty("sortDir") String sortDir,
        @JsonProperty("reverseSortDir") String reverseSortDir
        ) {}
