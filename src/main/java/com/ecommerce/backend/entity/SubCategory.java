package com.ecommerce.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "sub_categories")
@Getter
@Setter
public class SubCategory {
    @Id
    @GeneratedValue
    private UUID subCategoryId;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
