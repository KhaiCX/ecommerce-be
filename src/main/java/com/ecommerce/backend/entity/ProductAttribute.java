package com.ecommerce.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Entity
@Table(name = "product_attributes")
@Getter
@Setter
public class ProductAttribute {
    @Id
    @GeneratedValue
    private UUID productAttributeId;
    private String type;
    private String value;
}
