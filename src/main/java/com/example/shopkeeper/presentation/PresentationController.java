package com.example.shopkeeper.presentation;

import com.example.shopkeeper.product.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class PresentationController {
    private final ProductService products;
    public PresentationController(ProductService products){this.products=products;}
    @GetMapping("/products")
    public ApiResponse<List<ProductResponse>> products(@RequestParam(required=false) String q,@RequestParam(required=false) String category,@RequestParam(defaultValue="name") String sort){
        return ApiResponse.ok(products.search(q,category,sort).stream().map(ProductResponse::from).toList());
    }
    @GetMapping("/products/{id}") public ApiResponse<ProductResponse> product(@PathVariable Long id){return ApiResponse.ok(ProductResponse.from(products.get(id)));}
    @GetMapping("/categories") public ApiResponse<List<String>> categories(){return ApiResponse.ok(products.categories());}
}
