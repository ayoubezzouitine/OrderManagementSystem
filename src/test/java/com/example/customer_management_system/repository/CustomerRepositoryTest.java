package com.example.customer_management_system.repository;

import com.example.customer_management_system.exception.CustomerNotFoundException;
import com.example.customer_management_system.model.Customer;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(TestContainersConfig.DataSourceInitializer.class)
public class CustomerRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer1;
    private Customer customer2;
    private Customer customer3;
    private Customer customer4;
    private Customer customer5;
    private static boolean isSetupDone = false;

    @BeforeAll
    public static void setUp(@Autowired CustomerRepository customerRepository) {
        if (!isSetupDone) {
            // Setting up test data
            Customer customer1 = new Customer(null, "John Doe", "john.doe@example.com", null);
            Customer customer2 = new Customer(null, "Jane Doe", "jane.doe@example.com", null);
            Customer customer3 = new Customer(null, "Karim Ali", "karim.ali@example.com", null);
            Customer customer4 = new Customer(null, "Ahmed ko", "ahmed.ko@example.com", null);
            Customer customer5 = new Customer(null, "Adam Smith", "adam.smith@example.com", null);

            customerRepository.saveAll(List.of(customer1, customer2, customer3, customer4, customer5));
            isSetupDone = true;
        }
    }

    @Test
    public void saveCustomerTest() {
        Customer customer = new Customer(null
                , "Ziad stak", "ziad.stak@example.com", null);
        customerRepository.save(customer);

        List<Customer> customers = customerRepository.findAll();
        Assert.assertEquals(6, customers.size());
    }


    @Test
    public void getAllCustomersTest() {
        List<Customer> customers = customerRepository.findAll();
        Assert.assertEquals(5, customers.size());
    }

    @Test
    public void getCostumerByIdTest() {
        Long id = 1L;
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer with the id " + id + " is not found"));
        Assert.assertNotNull(customer);
        Assert.assertEquals(customer.getEmail(), "john.doe@example.com");
    }

    @Test
    public void getCostumerByIdThrowCustomerNotFoundExceptionTest() {
        Long id = 10L;
        // Then: Asserting that the CustomerNotFoundException is thrown
        assertThrows(CustomerNotFoundException.class, () -> {
            // Simulate the logic in the service layer
            customerRepository.findById(id)
                    .orElseThrow(() -> new CustomerNotFoundException("Customer with the id " + id + " is not found"));
        });
    }

    @Test
    public void updateCustomerTest() {
        long id = 1L;
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer with the id " + id + " is not found"));
        customer.setName("Kurt cobain");
        customer.setEmail("kurt.cobain@example.com");
        customerRepository.save(customer);
        Customer updatedCustomer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("Customer with the id " + id + " is not found"));
        Assert.assertEquals(updatedCustomer.getName(), "Kurt cobain");
        Assert.assertEquals(updatedCustomer.getEmail(), "kurt.cobain@example.com");
    }

    @Test
    public void deleteCustomerTest() {
        Long id = 1L;
        customerRepository.deleteById(id);
        List<Customer> customers = customerRepository.findAll();
        Assert.assertEquals(4, customers.size());
    }


}
