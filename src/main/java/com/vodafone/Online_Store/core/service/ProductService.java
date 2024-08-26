package com.vodafone.Online_Store.core.service;

import com.vodafone.Online_Store.core.domain.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product updateProduct(Long productId, Product product);
    void deleteProduct(Long productId);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
}
