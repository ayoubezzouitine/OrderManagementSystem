package com.example.customer_management_system.service;

import com.example.customer_management_system.model.Customer;

import java.util.List;

public interface CustomerService {
    public Customer createCustomer(Customer customer);
    public Customer updateCustomer(long id,Customer customer);
    public Customer getCustomerById(long id);
    public List<Customer> getAllCustomers();
    public void deleteCustomerById(long id);


}
