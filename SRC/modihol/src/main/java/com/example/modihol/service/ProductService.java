package com.example.modihol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.modihol.repository.ProductRepository;
import com.example.modihol.repository.ProductVariantRepository;
import com.example.modihol.repository.CategoryRepository;
import com.example.modihol.entity.*;
import com.example.modihol.dto.AddProductDTO;
import com.example.modihol.dto.ManageProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
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
    


    // Lấy Dữ liệu trả cho ManageProduct  và phân trang
    public Page<ManageProductDTO> getAllProducts(String keyword, Boolean status, String categoryName, Pageable pageable){
        if(keyword != null) keyword = keyword.trim();
        if(status != null && (status != false && status != true)) status = null;
        if(categoryName != null) categoryName = categoryName.trim();
        Page<ManageProductDTO> productPage = productRepo.getAllProducts(keyword, status, categoryName, pageable);
        List<ManageProductDTO> products = productPage.getContent();
        List<Object[]> sizeData = productRepo.getAllSizes();

        // Map: productId -> list size
        Map<Integer, List<String>> sizeMap = new HashMap<>();

        for(Object[] row : sizeData){
            Integer productId = (Integer) row[0];
            String size = (String) row[1];

            sizeMap.computeIfAbsent(productId, k -> new ArrayList<>()).add(size);
        }

        // set vào DTO
        for(ManageProductDTO p : products){
            p.setSize(sizeMap.getOrDefault(p.getId(), new ArrayList<>()));
        }

        return productPage;
    }
    // - Duyệt qua sizeData, mỗi phần tử là Object[] chứa productId và size
    // - Lấy productId từ row[0], size từ row[1]
    // - Dùng sizeMap để gom các size theo productId
    //     + nếu productId chưa có thì tạo list mới
    //     + nếu có rồi thì thêm size vào list

    // - Sau đó duyệt danh sách products
    // - Với mỗi product, lấy id và tìm size tương ứng trong sizeMap
    // - Nếu có thì set vào DTO, nếu không thì set list rỗng


}
