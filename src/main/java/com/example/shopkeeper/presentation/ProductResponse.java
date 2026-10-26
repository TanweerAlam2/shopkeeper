package com.example.shopkeeper.presentation;

import com.example.shopkeeper.product.Product;
import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        String category,
        BigDecimal price,
        BigDecimal originalPrice,
        String imageUrl,
        String description,
        Double rating,
        Integer stock,
        boolean available
) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getId(), product.getName(), product.getCategory(),
                product.getPrice(), product.getOriginalPrice(), product.getImageUrl(),
                product.getDescription(), product.getRating(), product.getStock(),
                product.getStock() != null && product.getStock() > 0
        );
    }
}
