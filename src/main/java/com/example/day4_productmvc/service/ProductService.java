package com.example.day4_productmvc.service;

import com.example.day4_productmvc.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductService extends JpaRepository<Product, String> {
    
}
