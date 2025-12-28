package com.ecommerce.backend.controller;

import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.model.request.ProductRequest;
import com.ecommerce.backend.model.response.PageResponse;
import com.ecommerce.backend.model.response.ProductDetailResponse;
import com.ecommerce.backend.model.response.ProductResponse;
import com.ecommerce.backend.model.response.Response;
import com.ecommerce.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rest/api")
public class ProductController {
    private static final int WINDOW_SIZE = 5;
    private final ProductService productService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/products")
    public ResponseEntity<List<Product>> getProductsByAdmin() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/products")
    public ResponseEntity<Response> addProduct(@RequestBody ProductRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.addProduct(request));
    }

    @GetMapping("/products")
    public ResponseEntity<ProductResponse> getDataProducts() {
        return getPageProducts(
                1,
                10,
                "name",
                "asc");
    }

    @GetMapping("/page")
    public ResponseEntity<ProductResponse> getPageProducts(
            @RequestParam(name = "pageNum") int pageNum
            , @RequestParam(name = "pageSize") int pageSize
            , @RequestParam(name = "sortField") String sortField
            , @RequestParam(name = "sortDir") String sortDir) {
        Page<Product> pageProduct = productService.getAll(pageNum, pageSize, sortField, sortDir);
        List<Product> products = pageProduct.getContent();
        long totalElements = pageProduct.getTotalElements();
        int totalPages = pageProduct.getTotalPages();
        int startPage = (pageNum / WINDOW_SIZE) * WINDOW_SIZE;
        int endPage = Math.min(startPage + WINDOW_SIZE - 1, totalPages - 1);
        String reverseSortDir = sortDir.equals("asc") ? "desc" : "asc";

        return ResponseEntity.ok().body(ProductResponse.builder()
                .products(products)
                .pageResponse(PageResponse.builder()
                        .pageNum(pageNum)
                        .pageSize(pageSize)
                        .currentPage(pageNum)
                        .totalPages(totalPages)
                        .totalItems(totalElements)
                        .startPage(startPage)
                        .endPage(endPage)
                        .sortField(sortField)
                        .sortDir(sortDir)
                        .reverseSortDir(reverseSortDir).build())
                .build());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDetailResponse> getProductDetail(@PathVariable UUID productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }
}
