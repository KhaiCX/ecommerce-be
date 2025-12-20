package com.ecommerce.backend.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record PageResponse(
        @JsonProperty("pageNum") int pageNum,
        @JsonProperty("pageSize") int pageSize,
        @JsonProperty("currentPage") int currentPage,
        @JsonProperty("totalPages") int totalPages,
        @JsonProperty("totalItems") long totalItems,
        @JsonProperty("startPage") int startPage,
        @JsonProperty("endPage") int endPage,
        @JsonProperty("sortField") String sortField,
        @JsonProperty("sortDir") String sortDir,
        @JsonProperty("reverseSortDir") String reverseSortDir) {}
