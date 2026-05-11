package com.example.modihol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.modihol.repository.ProductImageRepository;
import java.util.List;
@Service
public class ProductImageService {
    @Autowired
    private ProductImageRepository productImgRepo;

    public List<String> getImagesByProductId(Integer productId){
        return productImgRepo.getImagesByProductId(productId);
    }
}
