package com.ecommerce.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payment_details")
@Getter
@Setter
public class PaymentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentDetailId;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private Integer amount;
    private String provider;
    private String status;
}
