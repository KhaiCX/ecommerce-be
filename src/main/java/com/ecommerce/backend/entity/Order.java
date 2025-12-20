package com.ecommerce.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue
    private UUID orderId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private BigDecimal totalPrice;
}
