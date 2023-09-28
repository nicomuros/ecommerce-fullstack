package com.ecommerce.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table (name="products")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @SequenceGenerator(
            name="product_id_sequence",
            sequenceName="product_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_id_sequence"
    )
    Integer product_id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String description;
    @Column(nullable = false)
    Double price;
    @Column(nullable = false)
    Integer stock;

    public Product(String name, String description, Double price, Integer stock){
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }
}
