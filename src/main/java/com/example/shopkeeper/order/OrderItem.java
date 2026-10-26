package com.example.shopkeeper.order;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private String productName;
    private BigDecimal unitPrice;
    private int quantity;

    public OrderItem() {}
    public OrderItem(Long productId, String productName, BigDecimal unitPrice, int quantity) {
        this.productId=productId; this.productName=productName; this.unitPrice=unitPrice; this.quantity=quantity;
    }
    public Long getId(){return id;}
    public Long getProductId(){return productId;}
    public String getProductName(){return productName;}
    public BigDecimal getUnitPrice(){return unitPrice;}
    public int getQuantity(){return quantity;}
}
