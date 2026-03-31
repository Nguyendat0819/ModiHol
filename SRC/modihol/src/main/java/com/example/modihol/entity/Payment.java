package com.example.modihol.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import java.time.LocalDateTime;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentmethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name ="created_at", nullable = false)
    private LocalDateTime createdAt;

    // mối quan hệ
    @OneToOne
    @JoinColumn(name = "order_id", nullable = false) // mối quan hệ order 
    private Order order;

}
