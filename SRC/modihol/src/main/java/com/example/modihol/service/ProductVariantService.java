package com.example.modihol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.modihol.entity.*;
import com.example.modihol.repository.ProductRepository;
import com.example.modihol.repository.ProductVariantRepository;

import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.util.*;
@Service
public class ProductVariantService {
    @Autowired
    private ProductVariantRepository productVariantRepo;

    @Autowired
    private ProductRepository productRepo;

    @Transactional
    public void updateVariant(Integer id, String size, Integer stock){
        ProductVariant variant = productVariantRepo.findById(id).orElseThrow(() -> new RuntimeException());
        variant.setSize(size);
        variant.setStock(stock);
        productVariantRepo.save(variant);
    }

    @Transactional
    public ProductVariant addVariant(Integer producId, String size, Integer stock, BigDecimal price){
        Product product = productRepo.findById(producId).orElseThrow(() -> new IllegalArgumentException("sản phâm không tìm thấy Id" + producId));
        if(productVariantRepo.existsByProductAndSize(product, size)){
            throw new IllegalArgumentException("Size đã tồn tại");
        }
        ProductVariant variant = new ProductVariant();
        variant.setProduct(product);
        variant.setSize(size);
        variant.setStock(stock);
        variant.setPrice(price);
        return productVariantRepo.save(variant);   
    }


    @Transactional
    public void deleteVariant(Integer id){
        if(!productVariantRepo.existsById(id)){
            throw new RuntimeException("Không tìm thấy variant id: "+id);
        }
        productVariantRepo.deleteById(id);
    }
}
