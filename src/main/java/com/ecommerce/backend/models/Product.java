package com.ecommerce.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;


@Entity
@Table (
        name = "product",
        uniqueConstraints = {
                @UniqueConstraint(name = "product_name_unique",
                columnNames = "name"
                )
        }
)
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @SequenceGenerator(
            name="product_id_seq",
            sequenceName="product_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_id_seq"
    )
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private Boolean available;

    private String imgData;

    public Product(String name, String description, BigDecimal price, Boolean available, String imgData){
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
        this.imgData = imgData;
    }
}
