package com.example.modihol.dto;

import java.time.LocalDateTime;

import com.example.modihol.entity.OrderStatus;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class OrderSumaryDTO{
    private Long Id;
    private String username;
    private LocalDateTime createdAt;
    private BigDecimal totalAmount;
    private OrderStatus status  ;

    public OrderSumaryDTO(Long Id, String username, LocalDateTime createdAt, BigDecimal totalAmount, OrderStatus status) {
        this.Id = Id;
        this.username = username;
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
        this.status = status;
    }
}