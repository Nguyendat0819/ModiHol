package com.example.modihol.repository;
import com.example.modihol.dto.ManageProductDTO;
import com.example.modihol.entity.Product;
import com.example.modihol.entity.ProductImage;
import org.springframework.data.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query(
        value = """
            SELECT new com.example.modihol.dto.ManageProductDTO(
                p.id,
                p.productName,
                p.status,
                MIN(pv.price),
                SUM(pv.stock),
                c.name,
                (SELECT MIN(pi.imageUrl) FROM ProductImage pi WHERE pi.product.id = p.id)
            )
            FROM Product p 
            JOIN p.productVariants pv
            JOIN p.category c
            WHERE (:keyword IS NULL OR p.productName like %:keyword%)
            AND (:status IS NULL OR p.status = :status)
            AND (:categoryName IS NULL OR c.name like %:categoryName%)
            GROUP BY p.id, p.productName, p.status, c.name
            ORDER BY p.id ASC
        """,
        countQuery = """
            SELECT COUNT(p)
            FROM Product p
            JOIN p.category c
            WHERE (:keyword IS NULL OR p.productName like %:keyword%)
            AND (:status IS NULL OR p.status = :status)
            AND (:categoryName IS NULL OR c.name like %:categoryName%)
        """
    )
    Page<ManageProductDTO> getAllProducts(
    @Param("keyword") String keyword,
    @Param("status") Boolean status,
    @Param("categoryName") String categoryName,Pageable pageable
    );

    @Query("""
        SELECT DISTINCT pv.product.id,pv.size
        From ProductVariant pv
    """)
    List<Object[]> getAllSizes();


}
