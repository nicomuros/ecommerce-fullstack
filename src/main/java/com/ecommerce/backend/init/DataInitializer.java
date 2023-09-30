package com.ecommerce.backend.init;

import com.ecommerce.backend.models.Product;
import com.ecommerce.backend.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;

    public DataInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Product> productList = List.of(
                new Product("Hamburguesa", "Muy rica", 2000.0, 20),
                new Product("Coca cola","Fresquita",740.,20),
                new Product("Lomito","Con papafritas",4300.,10)
        );
        productRepository.saveAll(productList);
    }
}
