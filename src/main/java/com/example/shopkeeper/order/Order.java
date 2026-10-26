package com.example.shopkeeper.order;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String email;
    private String status = "PLACED";
    private BigDecimal total = BigDecimal.ZERO;
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    public Order() {}
    public Long getId(){return id;}
    public String getCustomerName(){return customerName;}
    public String getEmail(){return email;}
    public String getStatus(){return status;}
    public BigDecimal getTotal(){return total;}
    public LocalDateTime getCreatedAt(){return createdAt;}
    public List<OrderItem> getItems(){return items;}
    public void setCustomerName(String v){customerName=v;}
    public void setEmail(String v){email=v;}
    public void setStatus(String v){status=v;}
    public void setTotal(BigDecimal v){total=v;}
    public void setCreatedAt(LocalDateTime v){createdAt=v;}
    public void setItems(List<OrderItem> v){items=v;}
}
