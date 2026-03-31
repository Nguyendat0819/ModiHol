package com.example.modihol.entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "product_variants")

public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "price",nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "size", nullable = false, length = 10)
    private String size;

    @Column(name = "stock", nullable = false)
    private Integer stock;

    // quan hệ

    @OneToMany(mappedBy = "productVariant")
    private List<OrderItem> orderItems;
    
    @OneToMany(mappedBy = "productVariant")
    private List<CartItem> cartItems;


    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}
