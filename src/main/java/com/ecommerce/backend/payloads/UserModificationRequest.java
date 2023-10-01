package com.ecommerce.backend.payloads;

public record UserModificationRequest (
        String name,
        String lastName,
        Integer phone,
        String email
){}