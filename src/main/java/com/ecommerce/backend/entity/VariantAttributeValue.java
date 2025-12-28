package com.ecommerce.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "variant_attribute_values")
@Getter
@Setter
public class VariantAttributeValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long variantAttributeValueId;
    private String name;
    @ManyToOne
    @JoinColumn(name = "variant_attribute_id")
    private VariantAttribute variantAttribute;
    @ManyToOne
    @JoinColumn(name = "product_variant_id")
    private ProductVariant productVariant;
}
