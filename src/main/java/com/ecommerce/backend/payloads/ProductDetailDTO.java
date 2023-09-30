package com.ecommerce.backend.payloads;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDTO {
    Integer product_id;
    String name;
    String description;
    double price;
    Integer stock;
}
