package com.example.shopkeeper.product;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/products")
public class ProductApiController {
    private final ProductService service;
    public ProductApiController(ProductService service){this.service=service;}
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Product create(@Valid @RequestBody Product p){return service.create(p);}
    @PutMapping("/{id}") public Product update(@PathVariable Long id,@Valid @RequestBody Product p){return service.update(id,p);}
    @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){service.delete(id);}
    @GetMapping("/categories") public List<String> categories(){return service.categories();}
}
