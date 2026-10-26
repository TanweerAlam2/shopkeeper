package com.example.shopkeeper.order;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService service;
    public OrderController(OrderService service){ this.service=service; }
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Order create(@Valid @RequestBody OrderRequest request){ return service.create(request); }
    @GetMapping public List<Order> all(@RequestParam(required=false) String email){ return service.find(email); }
    @GetMapping("/{id}") public Order one(@PathVariable Long id){ return service.get(id); }
    @PatchMapping("/{id}/status") public Order status(@PathVariable Long id,@RequestParam String value){ return service.updateStatus(id,value); }
}
