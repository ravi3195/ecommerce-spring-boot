package com.ravicoding.ecommerce.dao;

import com.ravicoding.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Long>
{
    // Find product by category Id
    //// SELECT * FROM Product p WHERE categoryId=:id;
    Page<Product> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);


    // For search button in Angular component.. acts like---
    // SELECT * FROM Product p WHERE p.name LIKE CONCAT('%',:name,'%');
    Page<Product> findByNameContaining(@RequestParam("name") String name, Pageable pageable);

}
