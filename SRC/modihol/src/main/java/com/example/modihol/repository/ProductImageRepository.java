package com.example.modihol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.modihol.entity.ProductImage;
import java.util.List;
@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage,Integer> {
    @Query(
        """
        SELECT pi.imageUrl
        FROM ProductImage pi
        WHERE pi.product.id = :productId
    """)
    List<String> getImagesByProductId(@Param("productId") Integer productId);
}
