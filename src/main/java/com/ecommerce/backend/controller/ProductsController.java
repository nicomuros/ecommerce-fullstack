package com.ecommerce.backend.controller;

import com.ecommerce.backend.models.Product;
import com.ecommerce.backend.payloads.ProductDetailResponse;
import com.ecommerce.backend.payloads.ProductModificationRequest;
import com.ecommerce.backend.payloads.ProductRegistrationRequest;
import com.ecommerce.backend.payloads.ProductsResponse;
import com.ecommerce.backend.service.ProductService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductsController {


    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Product getProductDetailById(
            @PathVariable("productId") Integer productId) {
        return productService.getProduct(productId);
    }

    @PostMapping
    public void registerProduct(
            @RequestBody ProductRegistrationRequest request){
        productService.addProduct(request);
    }

    @DeleteMapping("/{productId}")
    public void removeProduct(
            @PathVariable("productId") Integer productId){
        productService.deleteProductById(productId);
    }

    @PutMapping("/{productId}")
    public void updateProduct(
            @PathVariable("productId") Integer productId,
            @RequestBody ProductModificationRequest productModificationRequest
    ){
        productService.updateProductById(productModificationRequest, productId);
    }
}
