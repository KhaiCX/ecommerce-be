package com.ecommerce.backend.controller;

import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.model.request.AuthenticationRequest;
import com.ecommerce.backend.model.request.UserRequest;
import com.ecommerce.backend.model.response.UserResponse;
import com.ecommerce.backend.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/api/admin/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    static int WINDOW_SIZE = 5;
    UserService userService;

    @GetMapping
    public ResponseEntity<UserResponse> getDataUser() {
        return getPageUser(1, 5, "email", "asc");
    }
    @GetMapping("/page")
    public ResponseEntity<UserResponse> getPageUser(@RequestParam(name = "pageNum") int pageNum
            , @RequestParam(name = "pageSize") int pageSize
            , @RequestParam(name = "sortField") String sortField
            , @RequestParam(name = "sortDir") String sortDir) {
        Page<User> pageUser = userService.getAll(pageNum, pageSize, sortField, sortDir);
        List<User> users = pageUser.getContent();
        long totalElements = pageUser.getTotalElements();
        int totalPages = pageUser.getTotalPages();
        int startPage = (pageNum / WINDOW_SIZE) * WINDOW_SIZE;
        int endPage = Math.min(startPage + WINDOW_SIZE - 1, totalPages - 1);
        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";

        return ResponseEntity.ok().body(UserResponse.builder()
                .users(users)
                .pageNum(pageNum)
                .pageSize(pageSize)
                .currentPage(pageNum)
                .totalPages(totalPages)
                .totalItems(totalElements)
                .startPage(startPage)
                .endPage(endPage)
                .sortField(sortField)
                .sortDir(sortDir)
                .reverseSortDir(reverseSortDir)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable UUID id) {
        return ResponseEntity.ok().body(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable UUID id, @RequestBody UserRequest request) {
        return ResponseEntity.ok().body(userService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK.value()).build();
    }

    @PostMapping("/add-admin-user")
    public ResponseEntity<User> addAdminUser(@RequestBody @Validated AuthenticationRequest request) {
        return ResponseEntity.ok().body(userService.addAdminUser(request));
    }

}
