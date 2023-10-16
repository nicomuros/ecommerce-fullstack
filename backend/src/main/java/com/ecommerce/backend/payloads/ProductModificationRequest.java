package com.ecommerce.backend.payloads;

import java.math.BigDecimal;

public record ProductModificationRequest(
        String name,
        String description,
        Integer price,
        Boolean available,
        String imgData
){}

