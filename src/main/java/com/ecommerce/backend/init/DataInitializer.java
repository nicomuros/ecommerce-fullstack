package com.ecommerce.backend.init;

import com.ecommerce.backend.models.Product;
import com.ecommerce.backend.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
                new Product("Hamburguesa", "Muy rica", new BigDecimal(2500), true , "img1"),
                new Product("Coca cola","Fresquita",new BigDecimal(400),false , "img2"),
                new Product("Lomito","Con papafritas",new BigDecimal(7400),true , "img13")
        );
        //productRepository.saveAll(productList);

    }
}
