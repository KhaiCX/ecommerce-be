package com.ecommerce.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product extends BaseEntity {
    @Id
    @GeneratedValue
    private UUID productId;
    @NotNull
    @Column(unique = true)
    private String name;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
