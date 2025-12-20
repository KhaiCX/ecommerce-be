package com.ecommerce.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Entity
@Table(name = "addresses")
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue
    private UUID addressId;
    private String address;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
