package com.example.modihol.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.example.modihol.dto.AddProductDTO;
import com.example.modihol.entity.Product;
import com.example.modihol.entity.ProductImage;
import com.example.modihol.repository.ProductImageRepository;
import com.example.modihol.repository.ProductRepository;
import java.util.List;
import java.util.Map;
import java.text.Normalizer;

@Service
public class ImageService {
    @Autowired private Cloudinary cloudinary;
    @Autowired private ProductImageRepository productImageRepo;
    @Autowired private ProductRepository productRepo;

    public String removeAccent(String input){
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{M}", "");
    }
    public void uploadImagesAsync( AddProductDTO dto, Product product ){
        if(dto.getImages() != null && !dto.getImages().isEmpty()){
            // List<ProductImage> images = new ArrayList<>();
            String categoryFolder = removeAccent(dto.getCategoryName())
                    .trim().toLowerCase().replaceAll("[^a-z0-9]", "_");
    
            String baseName = removeAccent(dto.getProductName())
                    .trim().toLowerCase().replaceAll("[^a-z0-9]", "_");
    
            int index = 1;
    
            for(MultipartFile file : dto.getImages()){
                try{
                    Map<String,Object> uploadFormImages = new HashMap<>();
    
                    uploadFormImages.put("folder","Modihol/" + categoryFolder);
                    uploadFormImages.put(
                        "public_id",
                        baseName + "_anh_" + index + "_" + System.currentTimeMillis()
                    );
    
                    Map uploadResult = cloudinary.uploader()
                            .upload(file.getBytes(), uploadFormImages);
    
                    String imageUrl = uploadResult.get("secure_url").toString();
    
                    ProductImage productImage = new ProductImage();
                    productImage.setProduct(product);
                    productImage.setImageUrl(imageUrl);
    
                    productImageRepo.save(productImage);
    
                    index++;
    
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
    
            System.out.println("upload thành công");
        }
    }
}
