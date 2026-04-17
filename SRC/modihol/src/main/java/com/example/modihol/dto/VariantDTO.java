package com.example.modihol.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class VariantDTO {
    private Integer id;
    private String size;
    private Integer stock;
    public VariantDTO(Integer id, String size, Integer stock){
        this.id = id;
        this.size = size;
        this.stock = stock;
    }

    public Integer getId(){
        return id;
    }
    public String getSize(){
        return size;
    }
    public Integer getStock(){
        return stock;
    }
}
