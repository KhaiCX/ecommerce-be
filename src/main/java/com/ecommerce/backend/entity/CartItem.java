package com.ecommerce.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Entity
@Table(name = "cart_items")
@Getter
@Setter
public class CartItem {
    @Id
    @GeneratedValue
    private UUID cartItemId;
    @ManyToOne
    @JoinColumn(name = "product_sku_id")
    private ProductSku productSku;
    private Integer quantity;
}
