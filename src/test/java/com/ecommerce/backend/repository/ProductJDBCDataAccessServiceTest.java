package com.ecommerce.backend.repository;
import static org.assertj.core.api.Assertions.assertThat;

import com.ecommerce.backend.AbstractTestcontainers;
import com.ecommerce.backend.models.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
                FAKER.lordOfTheRings().character()  + UUID.randomUUID(),
                FAKER.lordOfTheRings().location(),
                FAKER.number().numberBetween(1500, 5000),
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
        String productName = FAKER.lordOfTheRings().character()  + UUID.randomUUID();
        // Given
        Product product = new Product(
                productName,
                FAKER.lordOfTheRings().location(),
                FAKER.number().numberBetween(1500, 5000),
                FAKER.bool().bool(),
                FAKER.internet().url()
        );
        underTest.insertProduct(product);
        Integer id = underTest.selectAllProducts()
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
            assertThat(p.getName()).isEqualTo(product.getName());
            assertThat(p.getDescription()).isEqualTo(product.getDescription());
            assertThat(p.getPrice()).isEqualTo(product.getPrice());
            assertThat(p.getAvailable()).isEqualTo(product.getAvailable());
            assertThat(p.getImgData()).isEqualTo(product.getImgData());
        });
    }

    @Test
    void WillReturnEmptyWhenSelectCustomerById(){
        //Given
        Integer id = -1;

        //When
        var actual = underTest.selectProductById(id);

        //Then
        assertThat(actual).isEmpty();

    }
    @Test
    void insertProduct() {

        // Given
        String productName = FAKER.lordOfTheRings().character()  + UUID.randomUUID();
        Product product = new Product(
                productName,
                FAKER.lordOfTheRings().location(),
                FAKER.number().numberBetween(1500, 5000),
                FAKER.bool().bool(),
                FAKER.internet().url()
        );

        // When
        underTest.insertProduct(product);

        // Then
        Optional<Product> actual = underTest.selectAllProducts()
                .stream()
                .filter((p -> p.getName().equals(productName)))
                .findFirst();
        assertThat(actual).isPresent().hasValueSatisfying(p -> {
            assertThat(p.getName()).isEqualTo(product.getName());
            assertThat(p.getDescription()).isEqualTo(product.getDescription());
            assertThat(p.getPrice()).isEqualTo(product.getPrice());
            assertThat(p.getAvailable()).isEqualTo(product.getAvailable());
            assertThat(p.getImgData()).isEqualTo(product.getImgData());
        });
    }

    @Test
    void deleteProductById() {
        // Given
        String productName = FAKER.lordOfTheRings().character()  + UUID.randomUUID();
        Product product = new Product(
                productName,
                FAKER.lordOfTheRings().location(),
                FAKER.number().numberBetween(1500, 5000),
                FAKER.bool().bool(),
                FAKER.internet().url()
        );
        underTest.insertProduct(product);
        Integer id = underTest.selectAllProducts()
                .stream()
                .filter((p -> p.getName().equals(productName)))
                .map(Product::getId)
                .findFirst()
                .orElseThrow();
        // When
        underTest.deleteProductById(id);
        // Then
        Optional<Product> actual = underTest.selectProductById(id);
        assertThat(actual).isEmpty();
    }
    @Test
    void WillReturnFalseWhenDeleteProductById(){
        // Given
        String productName = FAKER.name().nameWithMiddle()  + UUID.randomUUID();
        // When
        Boolean actual = underTest.existProductWithName(productName);
        // Then
        assertThat(actual).isFalse();
    }
    @Test
    void WillReturnFalseWhenNoExistProductWithName(){
        // Given
        String productName = FAKER.name().nameWithMiddle()  + UUID.randomUUID();
        // When
        Boolean actual = underTest.existProductWithName(productName);
        // Then
        assertThat(actual).isFalse();
    }
    @Test
    void existProductWithName() {
        // Given
        String productName = FAKER.name().nameWithMiddle()  + UUID.randomUUID();
        Product product = new Product(
                productName,
                FAKER.lordOfTheRings().location(),
                FAKER.number().numberBetween(1500, 5000),
                FAKER.bool().bool(),
                FAKER.internet().url()
        );
        underTest.insertProduct(product);
        Integer id = underTest.selectAllProducts()
                .stream()
                .filter((p -> p.getName().equals(productName)))
                .map(Product::getId)
                .findFirst()
                .orElseThrow();
        // When
        Boolean actual = underTest.existProductWithName(productName);
        // Then
        assertThat(actual).isTrue();
    }

    @Test
    void willReturnFalseWhenNoExistProductWithId() {
        // Given
        Integer id = -1;
        // When
        Boolean actual = underTest.existProductWithId(id);
        // Then
        assertThat(actual).isFalse();
    }

    @Test
    void existProductWithId() {
        // Given
        String productName = FAKER.lordOfTheRings().character() + UUID.randomUUID();
        Product product = new Product(
                productName,
                FAKER.lordOfTheRings().location(),
                FAKER.number().numberBetween(1500, 5000),
                FAKER.bool().bool(),
                FAKER.internet().url()
        );
        underTest.insertProduct(product);
        Integer id = underTest.selectAllProducts()
                .stream()
                .filter((p -> p.getName().equals(productName)))
                .map(Product::getId)
                .findFirst()
                .orElseThrow();
        // When
        Boolean actual = underTest.existProductWithId(id);
        // Then
        assertThat(actual).isTrue();
    }

    @Test
    void updateProductName() {
        // Given
        String productName = FAKER.lordOfTheRings().character() + UUID.randomUUID();
        Product product = new Product(
                productName,
                FAKER.lordOfTheRings().location(),
                FAKER.number().numberBetween(1500, 5000),
                FAKER.bool().bool(),
                FAKER.internet().url()
        );
        underTest.insertProduct(product);
        Integer id = underTest.selectAllProducts()
                .stream()
                .filter((p -> p.getName().equals(productName)))
                .map(Product::getId)
                .findFirst()
                .orElseThrow();
        Product update = new Product();
        update.setId(id);
        update.setName(FAKER.university().name());

        // When
        underTest.updateProduct(update);

        // Then
        Optional<Product> actual = underTest.selectProductById(id);
        assertThat(actual).isPresent().hasValueSatisfying(p -> {
                assertThat(p.getId()).isEqualTo(id);
                assertThat(p.getName()).isEqualTo(update.getName());
                assertThat(p.getDescription()).isEqualTo(product.getDescription());
                assertThat(p.getPrice()).isEqualTo(product.getPrice());
                assertThat(p.getAvailable()).isEqualTo(product.getAvailable());
                assertThat(p.getImgData()).isEqualTo(product.getImgData());
            }
        );
    }

    @Test
    void updateProductDescription() {
        // Given
        String productName = FAKER.lordOfTheRings().character() + UUID.randomUUID();
        Product product = new Product(
                productName,
                FAKER.lordOfTheRings().location(),
                FAKER.number().numberBetween(1500, 5000),
                FAKER.bool().bool(),
                FAKER.internet().url()
        );
        underTest.insertProduct(product);
        Integer id = underTest.selectAllProducts()
                .stream()
                .filter((p -> p.getName().equals(productName)))
                .map(Product::getId)
                .findFirst()
                .orElseThrow();
        Product update = new Product();
        update.setId(id);
        update.setDescription(FAKER.artist().name());
        // When
        underTest.updateProduct(update);
        // Then
        Optional<Product> actual = underTest.selectProductById(id);
        assertThat(actual).isPresent().hasValueSatisfying(p -> {
                    assertThat(p.getId()).isEqualTo(id);
                    assertThat(p.getName()).isEqualTo(product.getName());
                    assertThat(p.getDescription()).isEqualTo(update.getDescription());
                    assertThat(p.getPrice()).isEqualTo(product.getPrice());
                    assertThat(p.getAvailable()).isEqualTo(product.getAvailable());
                    assertThat(p.getImgData()).isEqualTo(product.getImgData());
                }
        );
    }

    @Test
    void updateProductPrice() {
        // Given
        String productName = FAKER.lordOfTheRings().character() + UUID.randomUUID();
        Product product = new Product(
                productName,
                FAKER.lordOfTheRings().location(),
                FAKER.number().numberBetween(1500, 5000),
                FAKER.bool().bool(),
                FAKER.internet().url()
        );
        underTest.insertProduct(product);
        Integer id = underTest.selectAllProducts()
                .stream()
                .filter((p -> p.getName().equals(productName)))
                .map(Product::getId)
                .findFirst()
                .orElseThrow();
        Product update = new Product();
        update.setId(id);
        update.setPrice(FAKER.number().numberBetween(1500, 5000));

        // When
        underTest.updateProduct(update);
        // Then
        Optional<Product> actual = underTest.selectProductById(id);
        assertThat(actual).isPresent().hasValueSatisfying(p -> {
                    assertThat(p.getId()).isEqualTo(id);
                    assertThat(p.getName()).isEqualTo(product.getName());
                    assertThat(p.getDescription()).isEqualTo(product.getDescription());
                    assertThat(p.getPrice()).isEqualTo(update.getPrice());
                    assertThat(p.getAvailable()).isEqualTo(product.getAvailable());
                    assertThat(p.getImgData()).isEqualTo(product.getImgData());
                }
        );
    }

    @Test
    void updateProductAvailable() {
        // Given
        String productName = FAKER.lordOfTheRings().character() + UUID.randomUUID();
        Product product = new Product(
                productName,
                FAKER.lordOfTheRings().location(),
                FAKER.number().numberBetween(1500, 5000),
                false,
                FAKER.internet().url()
        );
        underTest.insertProduct(product);
        Integer id = underTest.selectAllProducts()
                .stream()
                .filter((p -> p.getName().equals(productName)))
                .map(Product::getId)
                .findFirst()
                .orElseThrow();
        Product update = new Product();
        update.setId(id);
        update.setAvailable(true);

        // When
        underTest.updateProduct(update);
        // Then
        Optional<Product> actual = underTest.selectProductById(id);
        assertThat(actual).isPresent().hasValueSatisfying(p -> {
                    assertThat(p.getId()).isEqualTo(id);
                    assertThat(p.getName()).isEqualTo(product.getName());
                    assertThat(p.getDescription()).isEqualTo(product.getDescription());
                    assertThat(p.getPrice()).isEqualTo(product.getPrice());
                    assertThat(p.getAvailable()).isEqualTo(update.getAvailable());
                    assertThat(p.getImgData()).isEqualTo(product.getImgData());
                }
        );
    }

    @Test
    void updateProductImgDesc() {
        // Given
        String productName = FAKER.lordOfTheRings().character() + UUID.randomUUID();
        Product product = new Product(
                productName,
                FAKER.lordOfTheRings().location(),
                FAKER.number().numberBetween(1500, 5000),
                FAKER.bool().bool(),
                FAKER.internet().url()
        );
        underTest.insertProduct(product);
        Integer id = underTest.selectAllProducts()
                .stream()
                .filter((p -> p.getName().equals(productName)))
                .map(Product::getId)
                .findFirst()
                .orElseThrow();
        Product update = new Product();
        update.setId(id);
        update.setImgData(FAKER.internet().url());

        // When
        underTest.updateProduct(update);
        // Then
        Optional<Product> actual = underTest.selectProductById(id);
        assertThat(actual).isPresent().hasValueSatisfying(p -> {
                    assertThat(p.getId()).isEqualTo(id);
                    assertThat(p.getName()).isEqualTo(product.getName());
                    assertThat(p.getDescription()).isEqualTo(product.getDescription());
                    assertThat(p.getPrice()).isEqualTo(product.getPrice());
                    assertThat(p.getAvailable()).isEqualTo(product.getAvailable());
                    assertThat(p.getImgData()).isEqualTo(update.getImgData());
                }
        );
    }


    @Test
    void willNoUpdateWhenProductIsTheSame() {
        // Given
        String productName = FAKER.lordOfTheRings().character() + UUID.randomUUID();
        Product product = new Product(
                productName,
                FAKER.lordOfTheRings().location(),
                FAKER.number().numberBetween(1500, 5000),
                FAKER.bool().bool(),
                FAKER.internet().url()
        );
        underTest.insertProduct(product);
        Integer id = underTest.selectAllProducts()
                .stream()
                .filter((p -> p.getName().equals(productName)))
                .map(Product::getId)
                .findFirst()
                .orElseThrow();
        Product update = new Product();
        update.setId(id);

        // When
        underTest.updateProduct(update);

        // Then
        Optional<Product> actual = underTest.selectProductById(id);
        assertThat(actual).isPresent().hasValueSatisfying(p -> {
                    assertThat(p.getId()).isEqualTo(id);
                    assertThat(p.getName()).isEqualTo(product.getName());
                    assertThat(p.getDescription()).isEqualTo(product.getDescription());
                    assertThat(p.getPrice()).isEqualTo(product.getPrice());
                    assertThat(p.getAvailable()).isEqualTo(product.getAvailable());
                    assertThat(p.getImgData()).isEqualTo(product.getImgData());
                }
        );
    }
    @Test
    void updateEntireProduct() {
        // Given
        String productName = FAKER.lordOfTheRings().character();
        Product product = new Product(
                productName,
                FAKER.lordOfTheRings().location(),
                FAKER.number().numberBetween(1500, 5000),
                FAKER.bool().bool(),
                FAKER.internet().url()
        );
        underTest.insertProduct(product);
        Integer id = underTest.selectAllProducts()
                .stream()
                .filter((p -> p.getName().equals(productName)))
                .map(Product::getId)
                .findFirst()
                .orElseThrow();
        Product updatedProduct = new Product(
                id,
                FAKER.dragonBall().character(),
                FAKER.lordOfTheRings().location(),
                FAKER.number().numberBetween(1500, 5000),
                FAKER.bool().bool(),
                FAKER.internet().url()
        );
        // When
        underTest.updateProduct(updatedProduct);
        // Then
        Optional<Product> actual = underTest.selectProductById(id);
        assertThat(actual).isPresent().hasValueSatisfying(p -> {
           assertThat(p.getId()).isEqualTo(updatedProduct.getId());
           assertThat(p.getName()).isEqualTo(updatedProduct.getName());
           assertThat(p.getDescription()).isEqualTo(updatedProduct.getDescription());
           assertThat(p.getPrice()).isEqualTo(updatedProduct.getPrice());
           assertThat(p.getImgData()).isEqualTo(updatedProduct.getImgData());
        });
    }
}