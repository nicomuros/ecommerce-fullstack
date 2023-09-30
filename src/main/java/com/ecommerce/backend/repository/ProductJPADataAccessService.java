package com.ecommerce.backend.repository;

import com.ecommerce.backend.models.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("jpa")
public class ProductJPADataAccessService implements ProductDao {

    private final ProductRepository productsRepository;

    public ProductJPADataAccessService(ProductRepository productRepository) {
        this.productsRepository = productRepository;
    }


    @Override
    public List<Product> selectAllCostumers() {
        return productsRepository.findAll();
    }

    @Override
    public Optional<Product> selectProductById(Integer productId) {
        return productsRepository.findById(productId);
    }
}
