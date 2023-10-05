package com.ecommerce.backend.repository;
import static org.assertj.core.api.Assertions.assertThat;

import com.ecommerce.backend.AbstractTestcontainers;
import com.ecommerce.backend.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

class ProductJDBCDataAccessServiceTest extends AbstractTestcontainers {

    private ProductJDBCDataAccessService underTest;
    private final ProductRowMapper productRowMapper = new ProductRowMapper();


    @BeforeEach
    void setUp() {
        underTest = new ProductJDBCDataAccessService(
                getJdbcTemplate(),
                productRowMapper
        );
    }

    @Test
    void selectAllProducts() {
        // Given

        Product product = new Product(
                FAKER.lordOfTheRings().character(),
                FAKER.lordOfTheRings().location(),
                new BigDecimal(FAKER.number().numberBetween(1500,5000)),
                FAKER.bool().bool(),
                FAKER.internet().url()
        );
        underTest.insertProduct(product);
        // When
        List<Product> actual = underTest.selectAllProducts();
        // Then
        assertThat(actual).isNotEmpty();
    }

    @Test
    void selectProductById() {
        String productName = FAKER.lordOfTheRings().character();
        // Given
        Product product = new Product(
                productName,
                FAKER.lordOfTheRings().location(),
                new BigDecimal(FAKER.number().numberBetween(1500,5000)),
                FAKER.bool().bool(),
                FAKER.internet().url()
        );
        underTest.insertProduct(product);
        Long id = underTest.selectAllProducts()
                .stream()
                .filter((p -> p.getName().equals(productName)))
                .map(Product::getId)
                .findFirst()
                .orElseThrow();

        // When
        Optional<Product> actual = underTest.selectProductById(id);
        // Then
        assertThat(actual).isPresent().hasValueSatisfying(p -> {
            assertThat(p.getId()).isEqualTo(id);
        });
    }

    @Test
    void insertProduct() {

        // Given
        String productName = FAKER.lordOfTheRings().character();
        Product product = new Product(
                productName,
                FAKER.lordOfTheRings().location(),
                new BigDecimal(FAKER.number().numberBetween(1500,5000)),
                FAKER.bool().bool(),
                FAKER.internet().url()
        );
        underTest.insertProduct(product);
        // When
        String actual = underTest.selectAllProducts()
                .stream()
                .map(Product::getName)
                .filter((name -> name.equals(productName)))
                .findFirst()
                .orElseThrow();
        // Then
        assertThat(actual).isEqualTo(productName);
    }

    @Test
    void deleteProductById() {
        // Given

        // When

        // Then
    }

    @Test
    void existProductWithName() {
        // Given

        // When

        // Then
    }

    @Test
    void existProductWithId() {
        // Given

        // When

        // Then
    }

    @Test
    void updateProduct() {
        // Given

        // When

        // Then
    }
}