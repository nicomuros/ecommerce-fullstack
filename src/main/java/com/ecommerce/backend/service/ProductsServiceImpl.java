package com.ecommerce.backend.service;

import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.models.Product;
import com.ecommerce.backend.payloads.ProductDTO;
import com.ecommerce.backend.payloads.ProductDetailDTO;
import com.ecommerce.backend.payloads.ProductDetailResponse;
import com.ecommerce.backend.payloads.ProductsResponse;
import com.ecommerce.backend.repository.ProductsRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class ProductsServiceImpl implements ProductsService{

    ProductsRepository productsRepository;
    ModelMapper modelMapper;

    @Autowired
    public ProductsServiceImpl(ProductsRepository productsRepository, ModelMapper modelMapper) {
        this.productsRepository = productsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductsResponse getAllProducts() {
        List<Product> products = productsRepository.findAll();
        List<ProductDTO> productDTOs = products
                .stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
        ProductsResponse productsResponse = new ProductsResponse();
        productsResponse.setContent(productDTOs);
        return productsResponse;
    }

    @Override
    public ProductDetailResponse getProductDetailById(Integer productId){
        Product product = productsRepository
                .findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: %s".formatted(productId)));
        ProductDetailDTO productDetailDTO = modelMapper.map(product, ProductDetailDTO.class);
        ProductDetailResponse productDetailResponse = new ProductDetailResponse();
        productDetailResponse.setContent(productDetailDTO);
        return productDetailResponse;
    }
}
