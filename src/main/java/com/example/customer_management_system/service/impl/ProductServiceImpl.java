package com.example.customer_management_system.service.impl;

import com.example.customer_management_system.model.Product;
import com.example.customer_management_system.repository.ProductRepository;
import com.example.customer_management_system.service.OrderService;
import com.example.customer_management_system.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.findById(id).orElseThrow(()->new RuntimeException("The product with the id " + id + " is not found"));
    }

    @Override
    public void deleteProduct(long id) {
     productRepository.findById(id).ifPresent(productRepository::delete);
    }
}
