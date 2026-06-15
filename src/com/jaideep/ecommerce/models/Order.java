package com.jaideep.ecommerce.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class Order implements Serializable {
    private final String id;
    private final String userId;
    private final List<OrderItem> items;
    private final double totalAmount;
    private final LocalDateTime createdAt;
    private OrderStatus status;

    public Order(String id, String userId, List<OrderItem> items, double totalAmount, LocalDateTime createdAt, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.items = items;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
        this.status = status;
    }

    public String getId() { return id; }
    public String getUserId() { return userId; }
    public List<OrderItem> getItems() { return items; }
    public double getTotalAmount() { return totalAmount; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
}
