package com.example.modihol.service;
import com.example.modihol.entity.User;
import com.example.modihol.entity.Order;
import com.example.modihol.dto.OrderSumaryDTO;
import com.example.modihol.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    // lấy danh sách để phân trang 
    public Page<Order> findByUserOrderByCreatedAtDesc(User user, Pageable pageable){
        return orderRepository.findByUserOrderByCreatedAtDesc(user, pageable);
    }

    
    // lấy danh sách đơn hàng cho dashboard admin 
    public Page<OrderSumaryDTO> getOrderSumary(Pageable pageable){
        return orderRepository.getOrderSumary(pageable);
    }
}
