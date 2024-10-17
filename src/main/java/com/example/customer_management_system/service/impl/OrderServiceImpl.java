package com.example.customer_management_system.service.impl;

import com.example.customer_management_system.exception.OrderNotFoundException;
import com.example.customer_management_system.model.Order;
import com.example.customer_management_system.repository.CustomerRepository;
import com.example.customer_management_system.repository.OrderRepository;
import com.example.customer_management_system.service.CustomerService;
import com.example.customer_management_system.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(long id, Order order) {
       return orderRepository.findById(id).map(existingOrder ->{
            existingOrder.setOrderDate(order.getOrderDate());
            existingOrder.setCustomer(order.getCustomer());
            existingOrder.setProducts(order.getProducts());
            return orderRepository.save(existingOrder);
        }).orElseThrow(() -> new OrderNotFoundException("Order with the id " + id + " is not found"));
    }

    @Override
    public Order findOrderById(long id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order with the id " + id + " is  not found"));
    }

    @Override
    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(long id) {
         orderRepository.findById(id).ifPresent(orderRepository::delete);
    }
}
