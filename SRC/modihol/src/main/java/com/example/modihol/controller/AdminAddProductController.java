package com.example.modihol.controller;
import com.example.modihol.dto.*;
import com.example.modihol.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import java.io.IOException;
@Controller
@RequestMapping("/admin")
public class AdminAddProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/addProduct") // đường dẫn cho link trên trang webiste 
    public String showAddProduct(){
        return "admin/addProduct";
    }

    @PostMapping("/addProductSubmit")
    public String addProductSubmit(
        @ModelAttribute AddProductDTO addProductDTO,
        Model model,
        RedirectAttributes redirectRedirectAttributes
    ){
        try{
            productService.addNewProduct(addProductDTO);
            redirectRedirectAttributes.addFlashAttribute("mesage","thêm sản phẩm thành công");
            redirectRedirectAttributes.addFlashAttribute("messageType","success");
            return"redirect:/admin/addProduct";
        }catch(IOException e){
            e.printStackTrace();
            model.addAttribute(e.getMessage());
            model.addAttribute("messagetype", "error");
            return"/addProduct";
        }
    }
}
