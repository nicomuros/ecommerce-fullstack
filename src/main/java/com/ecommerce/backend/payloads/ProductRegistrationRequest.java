package com.ecommerce.backend.payloads;

public record ProductRegistrationRequest (
    String name,
    String description,
    Double price,
    Integer stock
){}
