package com.ecommerce.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "product_skus")
@Getter
@Setter
public class ProductSku {
    @Id
    @GeneratedValue
    private UUID productSkuId;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "color_id")
    private ProductAttribute color;
    @ManyToOne
    @JoinColumn(name = "size_id")
    private ProductAttribute size;
    private Integer stock;
    private BigDecimal price;
}
