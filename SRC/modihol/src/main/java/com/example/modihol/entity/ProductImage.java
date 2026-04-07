package com.example.modihol.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "product_images")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "image_url", nullable = false, length = 255, columnDefinition = "TEXT")
    private String imageUrl;

    //  mối quan hệ 
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; 

    public void setID(Integer id){
        this.id = id;
    }
    public Integer getId(){
        return getId();
    }

    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public String getImageUrl(){
        return getImageUrl();
    }

    public void setProduct(Product product){
        this.product = product;
    }

    public Product getProduct(){
        return getProduct();
    }
}
