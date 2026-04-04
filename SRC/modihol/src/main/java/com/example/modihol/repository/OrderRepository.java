package com.example.modihol.repository;
import com.example.modihol.entity.Order;
import com.example.modihol.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.modihol.dto.OrderSumaryDTO;
import java.util.List;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{
    Page<Order> findByUserOrderByCreatedAtDesc(User user, Pageable pageable); // mai sau cho cho Order của user đăng nhập 
    @Query("""
        SELECT new com.example.modihol.dto.OrderSumaryDTO(
            o.Id,
            o.user.username,
            o.createdAt,
            o.totalAmount,
            o.status
        )
        FROM Order o
        JOIN o.user u
        ORDER BY o.createdAt Desc
        
    """)
    Page<OrderSumaryDTO> getOrderSumary(Pageable pageable); // Lấy danh sách cho dashboard và phân trang 
}