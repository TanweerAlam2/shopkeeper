package com.example.shopkeeper.order;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;
public record OrderRequest(
    @NotBlank String customerName,
    @Email @NotBlank String email,
    @NotEmpty List<@Valid Item> items
) {
    public record Item(@NotNull Long productId, @Min(1) int quantity) {}
}
