package com.ecommerce.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Entity
@Table(name = "order_details")
@Getter
@Setter
public class OrderDetail {
    @Id
    @GeneratedValue
    private UUID orderDetailId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private Integer total;
}
