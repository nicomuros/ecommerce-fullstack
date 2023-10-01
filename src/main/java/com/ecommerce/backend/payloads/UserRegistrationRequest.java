package com.ecommerce.backend.payloads;

public record UserRegistrationRequest (
        String name,
        String lastName,
        Integer phone,
        String email
){}