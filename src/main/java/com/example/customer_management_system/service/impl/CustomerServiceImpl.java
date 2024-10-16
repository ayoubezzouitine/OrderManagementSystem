package com.example.customer_management_system.service.impl;

import com.example.customer_management_system.exception.CustomerNotFoundException;
import com.example.customer_management_system.model.Customer;
import com.example.customer_management_system.repository.CustomerRepository;
import com.example.customer_management_system.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    // injection by constructor
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(long id, Customer customer) {
        return customerRepository.findById(id).map(existingCustomer -> {
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setName(customer.getName());
            existingCustomer.setOrders(customer.getOrders());

            // Save the updated customer to the database
            return customerRepository.save(existingCustomer);
        }).orElseThrow(() -> new CustomerNotFoundException("No customer found with id " + id));
    }

    @Override
    public Customer getCustomerById(long id) {
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer with the id "+ id + " is not found"));
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void deleteCustomerById(long id) {
        customerRepository.findById(id).ifPresent(customerRepository::delete);
    }
}
