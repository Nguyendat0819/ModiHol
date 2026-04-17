package com.example.modihol.dto;

import java.math.BigDecimal;

public class AddVariantDTO {
    private Integer productId;
    private String size;
    private Integer stock;
    private BigDecimal price;
    public AddVariantDTO(Integer productId, String size, Integer stock, BigDecimal price){
        this.productId = productId;
        this.size = size;
        this.stock = stock;
        this.price = price;
    }
    public Integer getProductId(){
        return productId;
    }

    public String getSize(){
        return size;
    }

    public Integer getStock(){
        return stock;
    }

    public BigDecimal getPrice(){
        return price;
    }
}
