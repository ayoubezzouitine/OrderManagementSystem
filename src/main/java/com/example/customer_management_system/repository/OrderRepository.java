package com.example.customer_management_system.repository;

import com.example.customer_management_system.model.Customer;
import com.example.customer_management_system.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
