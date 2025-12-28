package com.ecommerce.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue
    private Long orderId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private BigDecimal totalPrice;
}
