package com.ecommerce.backend.service;

import com.ecommerce.backend.exception.DuplicateResourceException;
import com.ecommerce.backend.exception.RequestValidationException;
import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.models.Product;
import com.ecommerce.backend.payloads.ProductModificationRequest;
import com.ecommerce.backend.payloads.ProductRegistrationRequest;
import com.ecommerce.backend.repository.ProductDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductDao productDao;
    private ProductService underTest;


    @BeforeEach
    void setUp() {
        underTest = new ProductService(productDao);
    }

    @Test
    void getAllProducts() {
        // When
        underTest.getAllProducts();
        // Then
        verify(productDao).selectAllProducts();
    }

    @Test
    void canGetProduct() {
        // Given
        Integer id = 1;
        Product product = new Product(
                id,
                "Nombre",
                "Descripcion",
                25,
                true,
                "sa");

        Mockito.when(productDao.selectProductById(id)).thenReturn(Optional.of(product));
        // When
        Product actual = underTest.getProduct(id);
        // Then
        assertThat(actual).isEqualTo(product);
    }

    @Test
    void willThrowWhenGetProductReturnsEmptyOptional(){
        // Given
        Integer id = 2;
        Mockito.when(productDao.selectProductById(id)).thenReturn(Optional.empty());

        // When
        // Then
        assertThatThrownBy(() -> underTest.getProduct(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining(
                        "No se encontró un producto con el id: " + id
                );
    }
    @Test
    void canAddProduct() {
        // Given
        String name = "Nico";
        // Puede ingresar el dato porque nos aseguramos de que no exista
        Mockito.when(productDao.existProductWithName(name)).thenReturn(false);

        ProductRegistrationRequest request = new ProductRegistrationRequest(
                name,
                "Descripcion",
                25,
                true,
                "sa");
        // When
        underTest.addProduct(request);
        // Then
        // Declaramos el capturador del argumento
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        // Capturamos el argumento
        verify(productDao).insertProduct(productArgumentCaptor.capture());

        // Verificamos que el argumento capturado tenga los mismos valores que el request
        // De esta forma, nos aseguramos que los valores sean identicos, o sea que no cambien
        Product capturedProduct = productArgumentCaptor.getValue();
        assertThat(capturedProduct.getId()).isNull();
        assertThat(capturedProduct.getName()).isEqualTo(request.name());
        assertThat(capturedProduct.getDescription()).isEqualTo(request.description());
        assertThat(capturedProduct.getPrice()).isEqualTo(request.price());
        assertThat(capturedProduct.getAvailable()).isEqualTo(request.available());
        assertThat(capturedProduct.getImageUrl()).isEqualTo(request.imageUrl());

    }

    @Test
    void willThrowWhenEmailExistWhileAddingAProduct(){
        // Given
        String name = "Nico";
        // Puede ingresar el dato porque nos aseguramos de que no exista
        Mockito.when(productDao.existProductWithName(name)).thenReturn(true);
        ProductRegistrationRequest request = new ProductRegistrationRequest(
                name,
                "Description",
                25,
                true,
                "sa");
        // When
        assertThatThrownBy(() -> underTest.addProduct(request))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessageContaining("Ya existe un producto con el nombre " + request.name());

        // Then
        verify(productDao, never()).insertProduct(any());
    }
    @Test
    void canDeleteProductById() {
        // Given
        Integer id = 1;
        Mockito.when(productDao.existProductWithId(id)).thenReturn(true);

        // When
        underTest.deleteProductById(id);
        // Then
        verify(productDao).deleteProductById(id);
    }

    @Test
    void willThrowWhenWrongIdIsPassedToDeleteProduct() {
        // Given
        Integer id = 1;
        Mockito.when(productDao.existProductWithId(id)).thenReturn(false);
        // When
        assertThatThrownBy(() -> underTest.deleteProductById(id))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("No se encontró un producto con el id: " + id);
        // Then
        verify(productDao, never()).deleteProductById(id);
    }

    @Test
    void canUpdateProductNameById() {
        // Given
        Integer id = 1;
        String name = "name";
        Product product = new Product(
                id,
                name,
                "Description",
                25,
                true,
                "sa");
        when(productDao.selectProductById(id)).thenReturn(Optional.of(product));

        ProductModificationRequest request = new ProductModificationRequest(
                "updated",
                null,
                null,
                null,
                null
        );
        when(productDao.existProductWithName(request.name())).thenReturn(false);
        // When
        underTest.updateProductById(request, id);
        // Then
        // Declaramos el capturador del argumento
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        // Capturamos el argumento
        verify(productDao).updateProduct(productArgumentCaptor.capture());

        // Verificamos que el argumento capturado tenga los mismos valores que el request
        // De esta forma, nos aseguramos que los valores sean identicos, o sea que no cambien
        Product capturedProduct = productArgumentCaptor.getValue();
        assertThat(capturedProduct.getId()).isEqualTo(id);
        assertThat(capturedProduct.getName()).isEqualTo(request.name());
        assertThat(capturedProduct.getDescription()).isEqualTo(product.getDescription());
        assertThat(capturedProduct.getPrice()).isEqualTo(product.getPrice());
        assertThat(capturedProduct.getAvailable()).isEqualTo(product.getAvailable());
        assertThat(capturedProduct.getImageUrl()).isEqualTo(product.getImageUrl());
    }

    @Test
    void willThrowWhenUpdateProductWithNewNameAlreadyTaken() {
        // Given
        Integer id = 1;
        String name = "name";
        Product product = new Product(
                id,
                name,
                "Description",
                25,
                true,
                "sa");
        when(productDao.selectProductById(id)).thenReturn(Optional.of(product));

        ProductModificationRequest request = new ProductModificationRequest(
                "updated",
                null,
                null,
                null,
                null
        );
        when(productDao.existProductWithName(request.name())).thenReturn(true);
        // When
        assertThatThrownBy(() -> underTest.updateProductById(request, id))
                .isInstanceOf(DuplicateResourceException.class)
                .hasMessageContaining("Ya existe un producto con el nombre " + request.name());
        // Then
        verify(productDao, never()).updateProduct(any());
    }

    @Test
    void canUpdateProductDescriptionById() {
        // Given
        Integer id = 1;
        Product product = new Product(
                id,
                "name",
                "Description",
                25,
                true,
                "sa");
        when(productDao.selectProductById(id)).thenReturn(Optional.of(product));

        ProductModificationRequest request = new ProductModificationRequest(
                null,
                "updated",
                null,
                null,
                null
        );
        // When
        underTest.updateProductById(request, id);
        // Then
        // Declaramos el capturador del argumento
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        // Capturamos el argumento
        verify(productDao).updateProduct(productArgumentCaptor.capture());

        // Verificamos que el argumento capturado tenga los mismos valores que el request
        // De esta forma, nos aseguramos que los valores sean identicos, o sea que no cambien
        Product capturedProduct = productArgumentCaptor.getValue();
        assertThat(capturedProduct.getId()).isEqualTo(id);
        assertThat(capturedProduct.getName()).isEqualTo(product.getName());
        assertThat(capturedProduct.getDescription()).isEqualTo(request.description());
        assertThat(capturedProduct.getPrice()).isEqualTo(product.getPrice());
        assertThat(capturedProduct.getAvailable()).isEqualTo(product.getAvailable());
        assertThat(capturedProduct.getImageUrl()).isEqualTo(product.getImageUrl());
    }
    @Test
    void canUpdateProductPriceNameById() {
        // Given
        Integer id = 1;
        Product product = new Product(
                id,
                "name",
                "Description",
                25,
                true,
                "sa");
        when(productDao.selectProductById(id)).thenReturn(Optional.of(product));

        ProductModificationRequest request = new ProductModificationRequest(
                null,
                null,
                55,
                null,
                null
        );
        // When
        underTest.updateProductById(request, id);
        // Then
        // Declaramos el capturador del argumento
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        // Capturamos el argumento
        verify(productDao).updateProduct(productArgumentCaptor.capture());

        // Verificamos que el argumento capturado tenga los mismos valores que el request
        // De esta forma, nos aseguramos que los valores sean identicos, o sea que no cambien
        Product capturedProduct = productArgumentCaptor.getValue();
        assertThat(capturedProduct.getId()).isEqualTo(id);
        assertThat(capturedProduct.getName()).isEqualTo(product.getName());
        assertThat(capturedProduct.getDescription()).isEqualTo(product.getDescription());
        assertThat(capturedProduct.getPrice()).isEqualTo(request.price());
        assertThat(capturedProduct.getAvailable()).isEqualTo(product.getAvailable());
        assertThat(capturedProduct.getImageUrl()).isEqualTo(product.getImageUrl());
    }
    @Test
    void canUpdateProductAvailableById() {
        // Given
        Integer id = 1;
        Product product = new Product(
                id,
                "name",
                "Description",
                25,
                true,
                "sa");
        when(productDao.selectProductById(id)).thenReturn(Optional.of(product));

        ProductModificationRequest request = new ProductModificationRequest(
                null,
                null,
                null,
                false,
                null
        );
        // When
        underTest.updateProductById(request, id);
        // Then
        // Declaramos el capturador del argumento
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        // Capturamos el argumento
        verify(productDao).updateProduct(productArgumentCaptor.capture());

        // Verificamos que el argumento capturado tenga los mismos valores que el request
        // De esta forma, nos aseguramos que los valores sean identicos, o sea que no cambien
        Product capturedProduct = productArgumentCaptor.getValue();
        assertThat(capturedProduct.getId()).isEqualTo(id);
        assertThat(capturedProduct.getName()).isEqualTo(product.getName());
        assertThat(capturedProduct.getDescription()).isEqualTo(product.getDescription());
        assertThat(capturedProduct.getPrice()).isEqualTo(product.getPrice());
        assertThat(capturedProduct.getAvailable()).isEqualTo(request.available());
        assertThat(capturedProduct.getImageUrl()).isEqualTo(product.getImageUrl());
    }
    @Test
    void canUpdateProductImgDataById() {
        // Given
        Integer id = 1;
        Product product = new Product(
                id,
                "name",
                "Description",
                25,
                true,
                "sa");
        when(productDao.selectProductById(id)).thenReturn(Optional.of(product));

        ProductModificationRequest request = new ProductModificationRequest(
                null,
                null,
                null,
                null,
                "fdsafsd"
        );
        // When
        underTest.updateProductById(request, id);
        // Then
        // Declaramos el capturador del argumento
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        // Capturamos el argumento
        verify(productDao).updateProduct(productArgumentCaptor.capture());

        // Verificamos que el argumento capturado tenga los mismos valores que el request
        // De esta forma, nos aseguramos que los valores sean identicos, o sea que no cambien
        Product capturedProduct = productArgumentCaptor.getValue();
        assertThat(capturedProduct.getId()).isEqualTo(id);
        assertThat(capturedProduct.getName()).isEqualTo(product.getName());
        assertThat(capturedProduct.getDescription()).isEqualTo(product.getDescription());
        assertThat(capturedProduct.getPrice()).isEqualTo(product.getPrice());
        assertThat(capturedProduct.getAvailable()).isEqualTo(product.getAvailable());
        assertThat(capturedProduct.getImageUrl()).isEqualTo(request.imageUrl());
    }
    @Test
    void canUpdateAllProductById() {
        // Given
        Integer id = 1;
        Product product = new Product(
                id,
                "name",
                "Description",
                25,
                true,
                "sa");
        when(productDao.selectProductById(id)).thenReturn(Optional.of(product));

        ProductModificationRequest request = new ProductModificationRequest(
                "updated",
                "updated",
                55,
                false,
                "fdsafdasfas"
        );
        // When
        underTest.updateProductById(request, id);
        // Then
        // Declaramos el capturador del argumento
        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        // Capturamos el argumento
        verify(productDao).updateProduct(productArgumentCaptor.capture());

        // Verificamos que el argumento capturado tenga los mismos valores que el request
        // De esta forma, nos aseguramos que los valores sean identicos, o sea que no cambien
        Product capturedProduct = productArgumentCaptor.getValue();
        assertThat(capturedProduct.getId()).isEqualTo(id);
        assertThat(capturedProduct.getName()).isEqualTo(request.name());
        assertThat(capturedProduct.getDescription()).isEqualTo(request.description());
        assertThat(capturedProduct.getPrice()).isEqualTo(request.price());
        assertThat(capturedProduct.getAvailable()).isEqualTo(request.available());
        assertThat(capturedProduct.getImageUrl()).isEqualTo(request.imageUrl());
    }
    @Test
    void willNoUpdateProductWhenNoNewData() {
        // Given
        Integer id = 1;
        Product product = new Product(
                id,
                "name",
                "Description",
                25,
                true,
                "sa");
        when(productDao.selectProductById(id)).thenReturn(Optional.of(product));

        ProductModificationRequest request = new ProductModificationRequest(
                null,
                null,
                null,
                null,
                null
        );
        // When
        assertThatThrownBy(() -> underTest.updateProductById(request, id))
                .isInstanceOf(RequestValidationException.class)
                .hasMessageContaining("El producto no se pudo modificar");
        // Then
        verify(productDao, never()).updateProduct(any());
    }
}