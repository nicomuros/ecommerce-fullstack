package com.ecommerce.backend.repository;

import com.ecommerce.backend.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class ProductJPADataAccessServiceTest {

    private ProductJPADataAccessService underTest;

    @Mock
    private ProductRepository productRepository;
    private AutoCloseable autoCloseable;
    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new ProductJPADataAccessService(productRepository);
    }

    @AfterEach
    void tearDown() throws Exception{
        autoCloseable.close();
    }
    @Test
    void selectAllProducts() {
        // When
        underTest.selectAllProducts();
        // Then
        verify(productRepository)
                .findAll();
    }

    @Test
    void selectProductById() { //verificamos que el id que pasamos en undertest sea el que recibe el productRepository
        // Given
        Long id = (long) 1;

        // When
        underTest.selectProductById(id);
        // Then
        verify(productRepository).findById(id);
    }

    @Test
    void insertProduct() {
        // Given
        Product product = new Product(
                (long) 1,
                "Nombre",
                "Descripcion",
                254,
                true,
                "http://"
        );
        // When
        underTest.insertProduct(product);
        // Then
        verify(productRepository).save(product);
    }

    @Test
    void deleteProductById() {
        // Given
        Long id = (long)1;
        // When
        underTest.deleteProductById(id);
        // Then
        verify(productRepository).deleteById(id);
    }

    @Test
    void updateProduct() {
        // Given
        Long id = (long) 1;
        Product product = new Product(
                id,
                "Nombre",
                "Descripcion",
                254,
                true,
                "http://"
        );
        Product update = new Product();
        product.setId(id);
        product.setName("Actualizado");
        // When
        underTest.updateProduct(update);
        // Then
        verify(productRepository).save(update);
    }

    @Test
    void existProductWithName() {
        // Given
        String name = "Nico";
        // When
        underTest.existProductWithName(name);
        // Then
        verify(productRepository).existsProductByName(name);
    }

    @Test
    void existProductWithId() {
        // Given
        Long id = (long) 1;
        // When
        underTest.existProductWithId(id);
        // Then
        verify(productRepository).existsProductById(id);
    }
}