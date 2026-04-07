package com.example.modihol.repository;
import com.example.modihol.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant,Integer>{
    
}
