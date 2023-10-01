package com.ecommerce.backend.service;

import com.ecommerce.backend.exception.DuplicateResourceException;
import com.ecommerce.backend.exception.RequestValidationException;
import com.ecommerce.backend.payloads.ProductModificationRequest;
import com.ecommerce.backend.payloads.ProductRegistrationRequest;
import com.ecommerce.backend.repository.ProductDao;
import com.ecommerce.backend.exception.ResourceNotFoundException;
import com.ecommerce.backend.models.Product;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductDao productDao;

    public ProductService(@Qualifier("ProductJpa") ProductDao productDao) {
        this.productDao = productDao;
    }


    public List<Product> getAllProducts() {
        return productDao.selectAllCostumers();
    }

    public Product getProduct(Integer productId) {
        return productDao.selectProductById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un producto con el id: " + productId));
    }

    public void addProduct(ProductRegistrationRequest productRegistrationRequest) {
        if (productDao.existProductWithName(productRegistrationRequest.name())) {
            throw new DuplicateResourceException("Ya existe un producto con el nombre " + productRegistrationRequest.name());
        }

        productDao.insertProduct(
                new Product(
                        productRegistrationRequest.name(),
                        productRegistrationRequest.description(),
                        productRegistrationRequest.price(),
                        productRegistrationRequest.stock()
                )
        );
    }

    public void deleteProductById(Integer productId) {
        if (!productDao.existProductWithId(productId)) {
            throw new ResourceNotFoundException("No se encontró un producto con el id: " + productId);
        }
        productDao.deleteProductById(productId);
    }

    public void updateProductById(ProductModificationRequest productModificationRequest, Integer productId) {
        Product product = productDao.selectProductById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un producto con el id: " + productId));

        boolean changed = false;

        if ((productModificationRequest.name() != null) && (!productModificationRequest.name().equals(product.getName()))) {
            if (productDao.existProductWithName(productModificationRequest.name())) {
                throw new DuplicateResourceException("Ya existe un producto con el nombre " + productModificationRequest.name());
            }
            product.setName(productModificationRequest.name());
            changed = true;
        }
        if ((productModificationRequest.description() != null) && (!productModificationRequest.description().equals(product.getDescription()))) {
            product.setDescription(productModificationRequest.description());
            changed = true;
        }
        if ((productModificationRequest.price() != null) && (!productModificationRequest.price().equals(product.getPrice()))) {
            product.setPrice(productModificationRequest.price());
            changed = true;
        }
        if ((productModificationRequest.stock() != null) && (!productModificationRequest.stock().equals(product.getStock()))) {
            product.setStock(productModificationRequest.stock());
            changed = true;
        }

        if (!changed) {
            throw new RequestValidationException("El producto no se pudo modificar");
        }

        productDao.updateProduct(product);
    }
}