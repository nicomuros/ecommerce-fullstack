package com.ecommerce.backend.service;

import com.ecommerce.backend.repository.ProductDao;
import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductDao productDao;

    public ProductService(@Qualifier("jpa") ProductDao productDao) {
        this.productDao = productDao;
    }


    public List<Product> getAllProducts() {
        return productDao.selectAllCostumers();
    }

    public Product getProduct(Integer productId){
        return productDao.selectProductById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un producto con el id: " + productId));
    }
}
