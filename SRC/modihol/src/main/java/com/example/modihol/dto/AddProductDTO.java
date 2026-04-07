package com.example.modihol.dto;
import org.springframework.web.multipart.MultipartFile;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
@Data
public class AddProductDTO {
    private String productName;
    private BigDecimal price;
    private String categoryName;
    private List<String> size;
    private List<Integer> stock;
    private List<MultipartFile> images;

    public String getProductName(){
        return productName;
    }
    public void setProductName(String productName){
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<String> getSize() {
        return size;
    }

    public void setSize(List<String> size) {
        this.size = size;
    }

    public List<Integer> getStock() {
        return stock;
    }

    public void setStock(List<Integer> stock) {
        this.stock = stock;
    }

    public List<MultipartFile> getImages(){
        return images;
    }
    public void setImages(List<MultipartFile> images){
        this.images = images;
    }
}
