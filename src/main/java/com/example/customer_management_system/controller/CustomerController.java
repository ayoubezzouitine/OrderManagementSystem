package com.example.customer_management_system.controller;

import com.example.customer_management_system.model.Customer;
import com.example.customer_management_system.repository.CustomerRepository;
import com.example.customer_management_system.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    // injector constructor for making life when i'm doing unit test
    public CustomerController(CustomerService customerService, CustomerRepository customerRepository) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable long id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable long id, @RequestBody Customer customer) {
        Customer updateCustomer = customerService.updateCustomer(id, customer);
        return ResponseEntity.ok(updateCustomer);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }

}
