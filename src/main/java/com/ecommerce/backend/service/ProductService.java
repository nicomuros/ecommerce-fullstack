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

    public ProductService(@Qualifier("ProductJDBC") ProductDao productDao) {
        this.productDao = productDao;
    }


    public List<Product> getAllProducts() {
        return productDao.selectAllProducts();
    }

    public Product getProduct(Long productId) {
        return productDao.selectProductById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un producto con el id: " + productId));
    }

    public void addProduct(ProductRegistrationRequest request) {
        if (productDao.existProductWithName(request.name())) {
            throw new DuplicateResourceException("Ya existe un producto con el nombre " + request.name());
        }

        productDao.insertProduct(
                new Product(
                        request.name(),
                        request.description(),
                        request.price(),
                        request.available(),
                        request.imgData()
                )
        );
    }

    public void deleteProductById(Long productId) {
        if (!productDao.existProductWithId(productId)) {
            throw new ResourceNotFoundException("No se encontró un producto con el id: " + productId);
        }
        productDao.deleteProductById(productId);
    }

    public void updateProductById(ProductModificationRequest request, Long productId) {
        Product product = productDao.selectProductById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró un producto con el id: " + productId));

        boolean changed = false;

        if ((request.name() != null) && (!request.name().equals(product.getName()))) {
            if (productDao.existProductWithName(request.name())) {
                throw new DuplicateResourceException("Ya existe un producto con el nombre " + request.name());
            }
            product.setName(request.name());
            changed = true;
        }
        if ((request.description() != null) && (!request.description().equals(product.getDescription()))) {
            product.setDescription(request.description());
            changed = true;
        }
        if ((request.price() != null) && (!request.price().equals(product.getPrice()))) {
            product.setPrice(request.price());
            changed = true;
        }

        if ((request.available() != null) && (!request.available().equals(product.getAvailable()))) {
            product.setAvailable(request.available());
            changed = true;
        }
        if ((request.imgData() != null) && (!request.imgData().equals(product.getImgData()))) {
            product.setImgData(request.imgData());
            changed = true;
        }
        if (!changed) {
            throw new RequestValidationException("El producto no se pudo modificar");
        }

        productDao.updateProduct(product);
    }
}