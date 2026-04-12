package com.example.modihol.dto;

import java.math.BigDecimal;

import lombok.Data;
import java.util.List;
@Data
public class ManageProductDTO {
    private Integer id;
    private String productName;
    private Boolean status;
    private BigDecimal price;
    private List<String> size;
    private Long stock;
    private String name;
    private String imageUrl;
    private List<VariantDTO> variants;

    public ManageProductDTO(Integer id,String productName, Boolean status,BigDecimal price,Long stock,String name,String imageUrl){
        this.id = id;
        this.productName = productName;
        this.status = status;
        this.price = price;
        this.stock = stock;
        this.name = name;
        this.imageUrl = imageUrl;
    }
    public Integer getId(){
        return id;
    }
    public void setSize(List<String> size) {
        this.size = size;
    }
    public void setVariants(List<VariantDTO> variants){
        this.variants = variants;
    }
}
