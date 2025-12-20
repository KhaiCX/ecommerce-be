package com.ecommerce.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Entity
@Table(name = "carts")
@Getter
@Setter
public class Cart extends BaseEntity{
    @Id
    @GeneratedValue
    private UUID cartId;
    private Integer total;
    @OneToOne
    private User user;
}
