package com.example.modihol.repository;
import com.example.modihol.entity.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant,Integer>{
    List<ProductVariant> getBySizeAndStock(String size, int stock);
}
