package com.example.shopkeeper.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service){ this.service=service; }
    @GetMapping("/")
    public String home(@RequestParam(required=false) String q,@RequestParam(required=false) String category,Model model){
        model.addAttribute("products",service.search(q,category,"name"));
        model.addAttribute("query",q==null?"":q); model.addAttribute("selectedCategory",category==null?"":category); return "index";
    }
    @GetMapping("/products/{id}") public String detail(@PathVariable Long id,Model model){ model.addAttribute("product",service.get(id)); return "product-detail"; }
    @GetMapping("/cart") public String cart(){return "cart";} @GetMapping("/checkout") public String checkout(){return "checkout";}
    @GetMapping("/account") public String account(){return "account";} @GetMapping("/orders") public String orders(){return "orders";}
    @GetMapping("/about") public String about(){return "about";} @GetMapping("/contact") public String contact(){return "contact";}
    @GetMapping("/api/products") @ResponseBody public java.util.List<Product> apiProducts(){return service.all();}
}
