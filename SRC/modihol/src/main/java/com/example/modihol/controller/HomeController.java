package com.example.modihol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // Lưu ý: Dùng @Controller thay vì @RestController
@RequestMapping("/user")
public class HomeController {

    @GetMapping("/home")
    public String showHomePage() {
        // Trả về file home.html nằm trong thư mục templates/user/
        return "user/home";
    }
}