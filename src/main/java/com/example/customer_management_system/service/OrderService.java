package com.example.customer_management_system.service;

import com.example.customer_management_system.model.Order;

import java.util.List;

public interface OrderService {
    public Order createOrder(Order order);
    public Order updateOrder(long id, Order order);
    public Order findOrderById(long id);
    public List<Order> findAllOrders();
    public void deleteOrder(long id);

}
