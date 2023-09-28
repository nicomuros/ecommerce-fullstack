package com.ecommerce.backend.service;

import com.ecommerce.backend.payloads.ProductDetailResponse;
import com.ecommerce.backend.payloads.ProductsResponse;

public interface ProductsService {
    ProductsResponse getAllProducts();
    ProductDetailResponse getProductDetailById(Integer productId);

}
