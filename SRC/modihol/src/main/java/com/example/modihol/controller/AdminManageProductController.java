package com.example.modihol.controller;
import com.example.modihol.dto.*;
import com.example.modihol.service.*;

import org.springframework.ui.Model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.*;
@Controller
@RequestMapping("/admin")
public class AdminManageProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
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
        return"admin/manageProduct";
    }
}
