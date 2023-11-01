package com.ecommerce.backend.payloads;

public record ProductModificationRequest(
        String name,
        String description,
        Integer price,
        Boolean available,
        String imageUrl
){}

