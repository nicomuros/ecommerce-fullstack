package com.ecommerce.backend.repository;

import com.ecommerce.backend.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    List<Product> selectAllCostumers();
    Optional<Product> selectProductById(Integer productId);
    void insertProduct(Product product);

    void deleteProductById(Integer productId);

    boolean existProductWithName(String name);

    boolean existProductWithId(Integer productId);

    void updateProduct(Product product);
}
