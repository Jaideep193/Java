package com.jaideep.ecommerce.models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Payment implements Serializable {
    private final String id;
    private final String orderId;
    private final String userId;
    private final PaymentMethod method;
    private final double amount;
    private final PaymentStatus status;
    private final LocalDateTime paidAt;

    public Payment(String id, String orderId, String userId, PaymentMethod method, double amount, PaymentStatus status, LocalDateTime paidAt) {
        this.id = id;
        this.orderId = orderId;
        this.userId = userId;
        this.method = method;
        this.amount = amount;
        this.status = status;
        this.paidAt = paidAt;
    }

    public String getId() { return id; }
    public String getOrderId() { return orderId; }
    public String getUserId() { return userId; }
    public PaymentMethod getMethod() { return method; }
    public double getAmount() { return amount; }
    public PaymentStatus getStatus() { return status; }
    public LocalDateTime getPaidAt() { return paidAt; }
}
