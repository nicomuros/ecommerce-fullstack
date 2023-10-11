package com.ecommerce.backend.repository;

import com.ecommerce.backend.models.Product;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository("Product-list")
public class ProductListDataAccessService implements ProductDao {

    // db
    private static final List<Product> productList;

    static {
        productList = List.of(
                new Product("Hamburguesa", "Muy rica", 2500, true , "img1"),
                new Product("Coca cola","Fresquita",400,false , "img2"),
                new Product("Lomito","Con papafritas",7400,true , "img13")
        );
    }

    @Override
    public List<Product> selectAllProducts() {
        return productList;
    }

    @Override
    public Optional<Product> selectProductById(Long id) {
        return productList.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
    }

    @Override
    public void insertProduct(Product customer) {
        productList.add(customer);
    }

    @Override
    public boolean existProductWithName(String name) {
        return productList.stream()
                .anyMatch(c -> c.getName().equals(name));
    }

    @Override
    public boolean existProductWithId(Long id) {
        return productList.stream()
                .anyMatch(c -> c.getId().equals(id));
    }

    @Override
    public void deleteProductById(Long productId) {
        productList.stream()
                .filter(c -> c.getId().equals(productId))
                .findFirst()
                .ifPresent(productList::remove);
    }

    @Override
    public void updateProduct(Product product) {
        productList.add(product);
    }

}
