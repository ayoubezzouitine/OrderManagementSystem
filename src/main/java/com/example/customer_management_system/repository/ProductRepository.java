package com.example.customer_management_system.repository;

import com.example.customer_management_system.model.Order;
import com.example.customer_management_system.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
