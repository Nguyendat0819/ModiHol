package com.example.modihol.controller;
import java.util.Map;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import com.example.modihol.service.OrderService;
import com.example.modihol.entity.Order;
import com.example.modihol.dto.OrderSumaryDTO;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    @Autowired
    private OrderService orderService;


  
    // view dashboard
    @GetMapping("/dashboard")
    public String showDashboard(
        @RequestParam(defaultValue = "0") int page,
        Model model
    ){
        // phân trang và lấy dữ liệu order sumary để hiển thị trên dashboard 
        Pageable pageable = PageRequest.of(page ,5);
        Page<OrderSumaryDTO> orderSumary = orderService.getOrderSumary(pageable);
        model.addAttribute("orderSumaryPage", orderSumary);
        model.addAttribute("baseUrl","/admin/dashboard");

        return "admin/dashboard";
    }
    // API cho dashboard 
    @GetMapping("/api/dashboard")
    @ResponseBody
    public Map<String, Object> showDashboardApi(){
        return Map.of("totalUser", 100);
    }
}
