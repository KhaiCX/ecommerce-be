package com.ecommerce.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Entity
@Table(name = "payment_details")
@Getter
@Setter
public class PaymentDetail {
    @Id
    @GeneratedValue
    private UUID paymentDetailId;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private Integer amount;
    private String provider;
    private String status;
}
