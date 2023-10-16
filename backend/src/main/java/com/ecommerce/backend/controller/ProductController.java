package com.ecommerce.backend.controller;

import com.ecommerce.backend.models.Product;
import com.ecommerce.backend.payloads.ProductModificationRequest;
import com.ecommerce.backend.payloads.ProductRegistrationRequest;
import com.ecommerce.backend.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {
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
