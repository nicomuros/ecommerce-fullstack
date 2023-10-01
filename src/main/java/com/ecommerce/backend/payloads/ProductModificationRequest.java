package com.ecommerce.backend.payloads;

public record ProductModificationRequest(
        String name,
        String description,
        Double price,
        Integer stock
){}

