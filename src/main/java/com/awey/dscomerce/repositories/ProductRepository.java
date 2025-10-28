package com.awey.dscomerce.repositories;

import com.awey.dscomerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
            SELECT obj FROM Product obj  
            WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%' , :name , '%'))
            """)
    Page<Product> serachByName(String name, Pageable pageable);

    @Query("SELECT p FROM Product p JOIN FETCH p.categories WHERE p.id = :id")
    Optional<Product> searchProductWithCategories(@Param("id") Long id);

}
