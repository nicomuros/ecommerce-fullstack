package com.ecommerce.backend.payloads;

public record ProductRegistrationRequest (
    String name,
    String description,
    Integer price,
    Boolean available,
    String imageUrl
){}
