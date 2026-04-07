package com.example.modihol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.modihol.repository.ProductRepository;
import com.example.modihol.repository.ProductVariantRepository;
import com.example.modihol.repository.CategoryRepository;
import com.example.modihol.entity.*;
import com.example.modihol.dto.AddProductDTO;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
@Service
public class ProductService {
    @Autowired private ProductRepository productRepo;
    @Autowired private ProductVariantRepository productVarriantRepo;
    @Autowired private CategoryRepository categoryRepo;
    @Autowired private ImageService imageService;
    // Thêm sản mới cho admin 
    @Transactional
    
    public void addNewProduct(AddProductDTO dto) throws IOException{
        // danh muc 
        Category category = categoryRepo.findByName(dto.getCategoryName());
        // thêm bảng category
        if(category == null){
            category  = new Category();
            category.setName(dto.getCategoryName());
            category = categoryRepo.save(category);
        }

        // 2 xử lý sản phẩm 
        // thêm bảng product
        Product product = new Product();
        product.setProductName(dto.getProductName());
        product.setStatus(true);
        product.setCategory(category);
        product.setCreatedAt(java.time.LocalDateTime.now());

        productRepo.saveAndFlush(product);

        // dùng cho ảnh
        imageService.uploadImagesAsync(dto,product);
               
        // thêm bảng productVariants
        BigDecimal price = dto.getPrice();
        if(price == null){
            throw new RuntimeException("giá tiền null");
        }

        if(dto.getSize() != null && dto.getStock() != null){
            List<ProductVariant> variants = new ArrayList<>();
            int min = Math.min(dto.getSize().size(),dto.getStock().size());
            for(int i = 0; i < min ; i++){
                String size = dto.getSize().get(i);
                Integer stock = dto.getStock().get(i);

                if(size != null && !size.isBlank()){
                    ProductVariant productVa = new ProductVariant();
                    productVa.setProduct(product);
                    productVa.setSize(size);
                    productVa.setStock(stock != null ? stock : 0);
                    productVa.setPrice(price);
                    variants.add(productVa);
                }
            }
            productVarriantRepo.saveAll(variants);
        }

    }
    
}
