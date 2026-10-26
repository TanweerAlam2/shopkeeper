package com.example.shopkeeper.order;

import com.example.shopkeeper.product.Product;
import com.example.shopkeeper.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orders;
    private final ProductRepository products;
    public OrderService(OrderRepository orders, ProductRepository products) { this.orders=orders; this.products=products; }

    @Transactional
    public Order create(OrderRequest request) {
        Order order = new Order();
        order.setCustomerName(request.customerName());
        order.setEmail(request.email().trim().toLowerCase());
        BigDecimal total = BigDecimal.ZERO;
        for (OrderRequest.Item item : request.items()) {
            Product product = products.findById(item.productId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found: " + item.productId()));
            if (item.quantity() > product.getStock())
                throw new IllegalArgumentException("Insufficient stock for " + product.getName());
            order.getItems().add(new OrderItem(product.getId(), product.getName(), product.getPrice(), item.quantity()));
            total = total.add(product.getPrice().multiply(BigDecimal.valueOf(item.quantity())));
            product.setStock(product.getStock() - item.quantity());
        }
        order.setTotal(total);
        return orders.save(order);
    }
    public List<Order> find(String email) {
        return email == null || email.isBlank() ? orders.findAll() : orders.findByEmailIgnoreCaseOrderByCreatedAtDesc(email);
    }
    public Order get(Long id) { return orders.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found: " + id)); }
    public Order updateStatus(Long id, String status) { Order order=get(id); order.setStatus(status.toUpperCase()); return orders.save(order); }
}
