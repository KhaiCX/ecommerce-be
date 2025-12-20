package com.ecommerce.backend.service;

import com.ecommerce.backend.entity.Category;
import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.model.request.CategoryRequest;
import com.ecommerce.backend.model.request.SoftDeleteRequest;
import com.ecommerce.backend.model.response.CategoryResponse;
import com.ecommerce.backend.repository.CategoryRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final EntityManager entityManager;

    public List<CategoryResponse> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories
                .stream()
                .map(category -> new CategoryResponse(category.getName(), category.getDeleted()))
                .toList();
    }

    public CategoryResponse getById(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return new CategoryResponse(category.getName(), category.getDeleted());
    }

    public CategoryResponse addCategory(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.name());
        Category savedCategory = categoryRepository.save(category);
        return new CategoryResponse(savedCategory.getName(), savedCategory.getDeleted());
    }
    public CategoryResponse updateCategoryById(CategoryRequest request, UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        category.setName(request.name());
        Category savedCategory = categoryRepository.save(category);
        return new CategoryResponse(savedCategory.getName(), savedCategory.getDeleted());
    }

    public void deleteById(UUID categoryId, SoftDeleteRequest request) {
        if (request.isSoftDelete()) {
            categoryRepository.softDeleteById(categoryId);
        }
        else {
            categoryRepository.deleteById(categoryId);
        }
    }

    public List<CategoryResponse> getAllForUser() {
        List<Category> categories = categoryRepository.findByDeletedFalse();
        return categories
                .stream()
                .map(category -> new CategoryResponse(category.getName(), category.getDeleted()))
                .toList();
    }

}
