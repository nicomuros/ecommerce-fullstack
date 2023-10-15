package com.ecommerce.backend.repository;

import com.ecommerce.backend.AbstractTestcontainers;
import com.ecommerce.backend.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest extends AbstractTestcontainers {

    @Autowired
    private ProductRepository underTest;
    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }
    @Test
    void existsProductByName() {
        // Given
        String productName = FAKER.name().nameWithMiddle()+FAKER.name().username();
        Product product = new Product(
                productName,
                FAKER.lordOfTheRings().location(),
                FAKER.number().numberBetween(1500, 5000),
                FAKER.bool().bool(),
                FAKER.internet().url()
        );
        underTest.save(product);

        // When
        Boolean actual = underTest.existsProductByName(productName);
        // Then
        assertThat(actual).isTrue();
    }
    @Test
    void existsProductByNameFailsWhenNotPresent() {
        // Given
        String productName = FAKER.name().nameWithMiddle()+FAKER.name().username();
        Product product = new Product(
                productName,
                FAKER.lordOfTheRings().location(),
                FAKER.number().numberBetween(1500, 5000),
                FAKER.bool().bool(),
                FAKER.internet().url()
        );
        underTest.save(product);

        // When
        Boolean actual = underTest.existsProductByName(FAKER.name().nameWithMiddle()+FAKER.name().username());
        // Then
        assertThat(actual).isFalse();
    }
    @Test
    void existsProductById() {
        // Given
        String productName = FAKER.name().nameWithMiddle()+FAKER.name().username();
        Product product = new Product(
                productName,
                FAKER.lordOfTheRings().location(),
                FAKER.number().numberBetween(1500, 5000),
                FAKER.bool().bool(),
                FAKER.internet().url()
        );
        underTest.save(product);
        Integer id = underTest.findAll()
                .stream()
                .filter((p -> p.getName().equals(productName)))
                .map(Product::getId)
                .findFirst()
                .orElseThrow();
        // When
        Boolean actual = underTest.existsProductById(id);
        // Then
        assertThat(actual).isTrue();
    }
}