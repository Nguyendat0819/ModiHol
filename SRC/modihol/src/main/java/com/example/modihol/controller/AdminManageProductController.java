package com.example.modihol.controller;
import com.example.modihol.dto.*;
import com.example.modihol.entity.ProductVariant;
import com.example.modihol.service.*;

import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.*;
@Controller
@RequestMapping("/admin")
public class AdminManageProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductVariantService productVariantService;
    @GetMapping("/manageProduct")
    public String viewManageProduct(
        Model model,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size,
        @Param ("keyword") String keyword,
        @Param ("status") Boolean status,
        @Param ("categoryName") String categoryName
    ){
        Page<ManageProductDTO> productList = productService.getAllProducts(keyword, status, categoryName, PageRequest.of( page, size));
        model.addAttribute("products",productList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("status", status);
        model.addAttribute("categoryName", categoryName);

        model.addAttribute("categories", categoryService.getAllCategory());

        // model.addAttribute("listSizeAndStock", productVariantService.getBySizeAndStock(products.size, products.stock ));
        return"admin/manageProduct";
    }


    // chỉnh sửa size và số lượng 
    @PostMapping("manageProduct/updateVariant")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> updateVariant(@RequestBody VariantDTO req){
        try{
            System.out.println("id:" + req.getId());
            System.out.println("size" + req.getSize());
            System.out.println("stock" + req.getStock());
            productVariantService.updateVariant(req.getId(), req.getSize(), req.getStock());
            return ResponseEntity.ok(Map.of("success", true));
        }catch(Exception e){
            e.printStackTrace();
            String msg = e.getMessage() != null ? e.getMessage() : e.getClass().getName();
            return ResponseEntity.ok(Map.of("success", false, "message", msg));
        }
    }

    @PostMapping("manageProduct/addVariants")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> addVariant(@RequestBody AddVariantDTO req){
        try{
            ProductVariant saved = productVariantService.addVariant(req.getProductId(), req.getSize(), req.getStock(), req.getPrice());
            return ResponseEntity.ok(Map.of("success",true,"variantId",saved.getId()));
        }catch(Exception e){
            e.printStackTrace();
            String msg = e.getMessage() != null ? e.getMessage() : e.getClass().getName();
            return ResponseEntity.ok(Map.of("success", false, "message", msg));
        }
    }


    @PostMapping("manageProduct/deleteStockAndSize")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> deleteVariant(@RequestBody VariantDTO req){
        try{
            productVariantService.deleteVariant(req.getId());
            return ResponseEntity.ok(Map.of("success",true));
        }catch(Exception e){
            e.printStackTrace();
            String msg = e.getMessage() != null ? e.getMessage() : e.getClass().getName();
            return ResponseEntity.ok(Map.of("success", false, "message", msg));
        }
    }
    
}
