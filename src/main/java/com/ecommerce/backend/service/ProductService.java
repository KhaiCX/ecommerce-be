package com.ecommerce.backend.service;

import com.ecommerce.backend.entity.Product;
import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.model.request.ProductRequest;
import com.ecommerce.backend.model.response.ProductDetailResponse;
import com.ecommerce.backend.model.response.Response;
import com.ecommerce.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Response addProduct(ProductRequest request) {
        Product product = new Product();
        product.setName(request.name());
        productRepository.save(product);
        return new Response("Created product successfully");
    }

    public Page<Product> getAll(int pageNum, int pageSize, String sortField, String sortDir) {
        Sort sort = sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        return productRepository.findAll(pageable);
    }

    public ProductDetailResponse getProductById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        return ProductDetailResponse.builder().product(product).build();
    }
}
