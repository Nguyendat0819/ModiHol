package com.example.modihol.dto;

import lombok.Data;

@Data
public class VariantDTO {
    private String size;
    private Integer stock;

    public VariantDTO(String size, Integer stock){
        this.size = size;
        this.stock = stock;
    }
}
