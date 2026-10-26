package com.example.shopkeeper.product;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;
    public ProductService(ProductRepository repository) { this.repository = repository; }

    public List<Product> search(String query, String category, String sort) {
        if (query != null && !query.isBlank()) return repository.findByNameContainingIgnoreCase(query);
        if (category != null && !category.isBlank()) return repository.findByCategoryIgnoreCase(category);
        Sort ordering = "price".equalsIgnoreCase(sort)
                ? Sort.by(Sort.Direction.ASC, "price")
                : Sort.by(Sort.Direction.ASC, "name");
        return repository.findAll(ordering);
    }

    public Product get(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
    }
    public List<Product> all() { return repository.findAll(); }
    public Product create(Product product) { product.setId(null); return repository.save(product); }
    public Product update(Long id, Product input) {
        Product old = get(id);
        old.setName(input.getName()); old.setCategory(input.getCategory());
        old.setPrice(input.getPrice()); old.setOriginalPrice(input.getOriginalPrice());
        old.setImageUrl(input.getImageUrl()); old.setDescription(input.getDescription());
        old.setRating(input.getRating()); old.setStock(input.getStock());
        return repository.save(old);
    }
    public void delete(Long id) { repository.delete(get(id)); }
    public List<String> categories() {
        return repository.findAll().stream().map(Product::getCategory)
                .filter(c -> c != null && !c.isBlank()).distinct().sorted().toList();
    }
}
