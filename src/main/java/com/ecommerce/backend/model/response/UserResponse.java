package com.ecommerce.backend.model.response;

import com.ecommerce.backend.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import java.util.List;

@Builder
public record UserResponse(
        @JsonProperty("users") List<User> users,
        @JsonProperty("page") PageResponse pageResponse) {}
