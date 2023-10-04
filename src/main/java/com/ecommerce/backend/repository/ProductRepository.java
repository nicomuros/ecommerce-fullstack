package com.ecommerce.backend.repository;

import com.ecommerce.backend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsProductByName(String name);

    boolean existsProductById(Long id);

}
