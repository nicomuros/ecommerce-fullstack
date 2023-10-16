package com.ecommerce.backend.repository;

import com.ecommerce.backend.models.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("ProductJpa")
public class ProductJPADataAccessService implements ProductDao {

    private final ProductRepository productsRepository;

    public ProductJPADataAccessService(ProductRepository productRepository) {
        this.productsRepository = productRepository;
    }


    @Override
    public List<Product> selectAllProducts() {
        return productsRepository.findAll();
    }

    @Override
    public Optional<Product> selectProductById(Integer productId) {
        return productsRepository.findById(productId);
    }

    @Override
    public void insertProduct(Product product) {
        productsRepository.save(product);
    }

    @Override
    public void deleteProductById(Integer productId){
        productsRepository.deleteById(productId);
    }

    public void updateProduct(Product product){
        productsRepository.save(product);
    }

    @Override
    public boolean existProductWithName(String name) {
        return productsRepository.existsProductByName(name);
    }

    @Override
    public boolean existProductWithId(Integer productId){
        return productsRepository.existsProductById(productId);
    }

}
