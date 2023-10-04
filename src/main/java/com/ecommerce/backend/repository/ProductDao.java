package com.ecommerce.backend.repository;

import com.ecommerce.backend.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    List<Product> selectAllProducts();
    Optional<Product> selectProductById(Long productId);
    void insertProduct(Product product);

    void deleteProductById(Long productId);

    boolean existProductWithName(String name);

    boolean existProductWithId(Long productId);

    void updateProduct(Product product);
}
