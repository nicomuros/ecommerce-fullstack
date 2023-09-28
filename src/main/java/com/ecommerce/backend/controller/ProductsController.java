package com.ecommerce.backend.controller;

import com.ecommerce.backend.payloads.ProductDetailResponse;
import com.ecommerce.backend.payloads.ProductsResponse;
import com.ecommerce.backend.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductsController {


    ProductsService productService;

    @Autowired
    public ProductsController(ProductsService productService){
        this.productService = productService;
    };

    @GetMapping("/public/products")
    public ResponseEntity<ProductsResponse> getAllProducts(){
        ProductsResponse productsResponse = productService.getAllProducts();
        return new ResponseEntity<>(productsResponse, HttpStatus.FOUND);
    }

    @GetMapping("/public/products/detail/{productId}")
    public ResponseEntity<ProductDetailResponse> getProductDetailById(
            @PathVariable("productId") Integer productId){
        ProductDetailResponse productDetailResponse = productService.getProductDetailById(productId);
        return new ResponseEntity<>(productDetailResponse, HttpStatus.FOUND);
    }
}
