package com.ecommerce.backend.controller;

import com.ecommerce.backend.models.Product;
import com.ecommerce.backend.payloads.ProductDetailResponse;
import com.ecommerce.backend.payloads.ProductsResponse;
import com.ecommerce.backend.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductsController {


    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/public/products")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/public/products/detail/{productId}")
    public Product getProductDetailById(
            @PathVariable("productId") Integer productId){
        return productService.getProduct(productId);
    }
}
