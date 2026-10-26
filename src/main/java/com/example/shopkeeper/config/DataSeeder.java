package com.example.shopkeeper.config;

import com.example.shopkeeper.product.Product;
import com.example.shopkeeper.product.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;

@Configuration
public class DataSeeder {
    @Bean
    CommandLineRunner seedProducts(ProductRepository repository) {
        return args -> {
            if (repository.count() > 0) return;
            repository.save(new Product("Noise Cancelling Headphones", "Electronics",
                    new BigDecimal("89.99"), new BigDecimal("129.99"),
                    "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?auto=format&fit=crop&w=900&q=80",
                    "Immersive sound, all-day comfort and reliable wireless performance.", 4.8, 42));
            repository.save(new Product("Minimal Leather Backpack", "Fashion",
                    new BigDecimal("59.00"), new BigDecimal("79.00"),
                    "https://images.unsplash.com/photo-1553062407-98eeb64c6a62?auto=format&fit=crop&w=900&q=80",
                    "A durable everyday backpack with a clean, modern silhouette.", 4.6, 18));
            repository.save(new Product("Smart Fitness Watch", "Electronics",
                    new BigDecimal("74.50"), new BigDecimal("99.00"),
                    "https://images.unsplash.com/photo-1523275335684-37898b6baf30?auto=format&fit=crop&w=900&q=80",
                    "Track workouts, sleep, heart rate and notifications from your wrist.", 4.7, 27));
            repository.save(new Product("Ceramic Coffee Set", "Home",
                    new BigDecimal("34.99"), new BigDecimal("44.99"),
                    "https://images.unsplash.com/photo-1514432324607-a09d9b4aefdd?auto=format&fit=crop&w=900&q=80",
                    "A simple, elegant set for slow mornings and shared conversations.", 4.5, 31));
        };
    }
}
