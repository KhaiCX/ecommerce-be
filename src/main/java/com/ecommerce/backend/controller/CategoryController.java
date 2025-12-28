package com.ecommerce.backend.controller;

import com.ecommerce.backend.model.request.CategoryRequest;
import com.ecommerce.backend.model.request.SoftDeleteRequest;
import com.ecommerce.backend.model.response.CategoryResponse;
import com.ecommerce.backend.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rest/api")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/categories")
    public ResponseEntity<List<CategoryResponse>> getDataCategories() {
        return ResponseEntity
                .ok(categoryService.getCategories());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/categories/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Integer id) {
        return ResponseEntity
                .ok()
                .body(categoryService.getById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/categories")
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody CategoryRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryService.addCategory(request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/categories/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(
            @PathVariable Integer id,
            @RequestBody CategoryRequest request) {
        return ResponseEntity
                .ok(categoryService.updateCategoryById(request, id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/categories/{id}")
    public ResponseEntity<Void> deleteById(
            @PathVariable Integer id,
            @RequestBody SoftDeleteRequest request) {
        categoryService.deleteById(id, request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> getAllForUser() {
        return ResponseEntity
                .ok(categoryService.getAllForUser());
    }

}
