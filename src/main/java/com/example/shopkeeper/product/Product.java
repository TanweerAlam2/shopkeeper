package com.example.shopkeeper.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String category;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal price;

    @DecimalMin("0.0")
    private BigDecimal originalPrice;

    @NotBlank
    private String imageUrl;

    @Column(length = 1000)
    private String description;

    private double rating = 4.5;
    private int stock = 20;

    public Product() {}

    public Product(String name, String category, BigDecimal price, BigDecimal originalPrice,
                   String imageUrl, String description, double rating, int stock) {
        this.name = name; this.category = category; this.price = price;
        this.originalPrice = originalPrice; this.imageUrl = imageUrl;
        this.description = description; this.rating = rating; this.stock = stock;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public BigDecimal getPrice() { return price; }
    public BigDecimal getOriginalPrice() { return originalPrice; }
    public String getImageUrl() { return imageUrl; }
    public String getDescription() { return description; }
    public double getRating() { return rating; }
    public int getStock() { return stock; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setCategory(String category) { this.category = category; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public void setOriginalPrice(BigDecimal originalPrice) { this.originalPrice = originalPrice; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setDescription(String description) { this.description = description; }
    public void setRating(double rating) { this.rating = rating; }
    public void setStock(int stock) { this.stock = stock; }
}
