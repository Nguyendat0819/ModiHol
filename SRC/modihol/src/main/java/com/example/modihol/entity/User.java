package com.example.modihol.entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.ToString;
import jakarta.persistence.Id;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username", nullable = false,length = 100)
    private String username;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email; 

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "reset_token", length = 255)
    private String resetToken;

    @Column(name = "role", length = 20)
    private String role;
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // quan hệ 
    // orders 
    @OneToMany(mappedBy="user")
    @ToString.Exclude 
    private List<Order> orders;

    @OneToOne(mappedBy="user") // cart 
    private Cart cart;

    @OneToMany(mappedBy="user") // address 
    @ToString.Exclude 
    private List<ShippingAddress> shippingAddresses;
    
    
}
