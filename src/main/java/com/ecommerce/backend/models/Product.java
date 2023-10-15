package com.ecommerce.backend.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table (
        name = "product",
        uniqueConstraints = {
                @UniqueConstraint(name = "product_name_unique",
                columnNames = "name"
                )
        }
)
public class Product {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Integer price;
    @Column(nullable = false)
    private Boolean available;

    private String imgData;

    public Product(String name, String description, Integer price, Boolean available, String imgData){
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
        this.imgData = imgData;
    }

    public Product(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product() {}

    public Product(Integer id, String name, String description, Integer price, Boolean available, String imgData) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
        this.imgData = imgData;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public String getImgData() {
        return imgData;
    }

    public void setImgData(String imgData) {
        this.imgData = imgData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(price, product.price) && Objects.equals(available, product.available) && Objects.equals(imgData, product.imgData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, available, imgData);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", available=" + available +
                ", imgData='" + imgData + '\'' +
                '}';
    }
}
