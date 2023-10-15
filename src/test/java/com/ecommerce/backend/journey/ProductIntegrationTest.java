package com.ecommerce.backend.journey;

import com.ecommerce.backend.models.Product;
import com.ecommerce.backend.payloads.ProductModificationRequest;
import com.ecommerce.backend.payloads.ProductRegistrationRequest;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

//Estamos trabajando con la base de datos directamente en docker
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;
    private static final  String PRODUCT_URI = "/api/product";
    @Test
    void canRegisterAProduct() {
        // Create a registration request
        Faker faker = new Faker();
        String name = faker.commerce().productName() + UUID.randomUUID();
        String description = faker.dragonBall().character();
        Integer price = 25;
        Boolean available = false;
        String imgData = faker.internet().url();

        ProductRegistrationRequest request = new ProductRegistrationRequest(
                name,
                description,
                price,
                available,
                imgData
        );

        // Send a post request
        webTestClient.post()
                .uri(PRODUCT_URI)
                .accept(MediaType.APPLICATION_JSON) // El cliente espera una respuesta en formato JSON
                .contentType(MediaType.APPLICATION_JSON) // El body de la request va a estar en formato JSON
                .body(Mono.just(request), ProductRegistrationRequest.class) //Se configura el cuerpo, compuesto por un objeto PRR.class
                .exchange() //Enviamos la request
                .expectStatus() //Seteamos la escucha de la respuesta
                .isOk(); //La respuesta debe ser esperada
        // Get the response (all products)
        List<Product> allProducts = webTestClient.get()
                .uri(PRODUCT_URI)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<Product>() {})
                .returnResult()
                .getResponseBody();

        Product expectedProduct = new Product(
                name, description, price, available, imgData
        );
        // Make sure that the products are present
        assertThat(allProducts)
                .isNotEmpty()
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
                .contains(expectedProduct);


        // Get product by id
        Integer id = allProducts.stream()
                        .filter(product -> product.getName().equals(name))
                        .map(Product::getId)
                        .findFirst()
                        .orElseThrow();
        expectedProduct.setId(id);
        webTestClient.get()
                .uri(PRODUCT_URI + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(new ParameterizedTypeReference<Product>() {})
                .isEqualTo(expectedProduct);
    }

    @Test
    void canDeleteProduct() {
        // Create a registration request
        Faker faker = new Faker();
        String name = faker.commerce().productName() + UUID.randomUUID();
        String description = faker.dragonBall().character();
        Integer price = 25;
        Boolean available = false;
        String imgData = faker.internet().url();

        ProductRegistrationRequest request = new ProductRegistrationRequest(
                name,
                description,
                price,
                available,
                imgData
        );

        // Send a post request
        webTestClient.post()
                .uri(PRODUCT_URI)
                .accept(MediaType.APPLICATION_JSON) // El cliente espera una respuesta en formato JSON
                .contentType(MediaType.APPLICATION_JSON) // El body de la request va a estar en formato JSON
                .body(Mono.just(request), ProductRegistrationRequest.class) //Se configura el cuerpo, compuesto por un objeto PRR.class
                .exchange() //Enviamos la request
                .expectStatus() //Seteamos la escucha de la respuesta
                .isOk(); //La respuesta debe ser esperada

        // Get the response (all products)
        List<Product> allProducts = webTestClient.get()
                .uri(PRODUCT_URI)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<Product>() {
                })
                .returnResult()
                .getResponseBody();


        // Get product by id
        Integer id = allProducts.stream()
                .filter(product -> product.getName().equals(name))
                .map(Product::getId)
                .findFirst()
                .orElseThrow();


        // Delete
        webTestClient.delete()
                .uri(PRODUCT_URI + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk();

        // We expect a 404 when we send a get request with the id
        webTestClient.get()
                .uri(PRODUCT_URI + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isNotFound();
    }


    @Test
    void canModifyAProduct() {
        // Create the original product
        Faker faker = new Faker();
        String name = faker.commerce().productName() + UUID.randomUUID();
        String description = faker.dragonBall().character();
        Integer price = 25;
        Boolean available = false;
        String imgData = faker.internet().url();

        ProductRegistrationRequest request = new ProductRegistrationRequest(
                name,
                description,
                price,
                available,
                imgData
        );

        // Send a post request
        webTestClient.post()
                .uri(PRODUCT_URI)
                .accept(MediaType.APPLICATION_JSON) // El cliente espera una respuesta en formato JSON
                .contentType(MediaType.APPLICATION_JSON) // El body de la request va a estar en formato JSON
                .body(Mono.just(request), ProductRegistrationRequest.class) //Se configura el cuerpo, compuesto por un objeto PRR.class
                .exchange() //Enviamos la request
                .expectStatus() //Seteamos la escucha de la respuesta
                .isOk(); //La respuesta debe ser esperada

        // Get all products
        List<Product> allProducts = webTestClient.get()
                .uri(PRODUCT_URI)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(new ParameterizedTypeReference<Product>() {})
                .returnResult()
                .getResponseBody();

        // Get the original product id
        Integer id = allProducts.stream()
                .filter(product -> product.getName().equals(name))
                .map(Product::getId)
                .findFirst()
                .orElseThrow();

        // Creating a modified product
        String newName = faker.commerce().productName() + UUID.randomUUID();
        String newDescription = faker.dragonBall().character();
        Integer newPrice = 500;
        Boolean newAvailable = !available;
        String newImgData = faker.internet().url();

        ProductModificationRequest modificationRequest = new ProductModificationRequest(
                newName,
                newDescription,
                newPrice,
                newAvailable,
                newImgData
        );

        // Send a put request
        webTestClient.put()
                .uri(PRODUCT_URI + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON) // El cliente espera una respuesta en formato JSON
                .contentType(MediaType.APPLICATION_JSON) // El body de la request va a estar en formato JSON
                .body(Mono.just(modificationRequest), ProductModificationRequest.class) //Se configura el cuerpo, compuesto por un objeto PRR.class
                .exchange() //Enviamos la request
                .expectStatus() //Seteamos la escucha de la respuesta
                .isOk(); //La respuesta debe ser esperada


        // Ask for de updated product
        Product receivedProduct = webTestClient.get()
                .uri(PRODUCT_URI + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Product.class)
                .returnResult()
                .getResponseBody();

        Product expectedProduct = new Product(
                id,
                newName,
                newDescription,
                newPrice,
                newAvailable,
                newImgData
        );

        assertThat(receivedProduct).isEqualTo(expectedProduct);
    }
}
