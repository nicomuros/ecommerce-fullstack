package com.ecommerce.backend.repository;

import com.ecommerce.backend.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductDao {
    List<Product> selectAllCostumers();
    Optional<Product> selectProductById(Integer productId);
}
