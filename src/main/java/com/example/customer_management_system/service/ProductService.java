package com.example.customer_management_system.service;

import com.example.customer_management_system.model.Product;

import java.util.List;

public interface ProductService {
    public Product createProduct(Product product);

    public Product updateProduct(long id,Product product);

    public List<Product> getAllProducts();

    public Product getProductById(long id);

    public void deleteProduct(long id);
}
