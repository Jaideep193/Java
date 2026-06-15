package com.jaideep.ecommerce.persistence;

import com.jaideep.ecommerce.models.CartItem;
import com.jaideep.ecommerce.models.Order;
import com.jaideep.ecommerce.models.Payment;
import com.jaideep.ecommerce.models.Product;
import com.jaideep.ecommerce.models.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppData implements Serializable {
    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Product> products = new HashMap<>();
    private final Map<String, List<CartItem>> cartsByUserId = new HashMap<>();
    private final Map<String, List<Order>> ordersByUserId = new HashMap<>();
    private final Map<String, List<Payment>> paymentsByUserId = new HashMap<>();

    public Map<String, User> getUsers() { return users; }
    public Map<String, Product> getProducts() { return products; }
    public Map<String, List<CartItem>> getCartsByUserId() { return cartsByUserId; }
    public Map<String, List<Order>> getOrdersByUserId() { return ordersByUserId; }
    public Map<String, List<Payment>> getPaymentsByUserId() { return paymentsByUserId; }

    public List<Order> getAllOrders() {
        List<Order> allOrders = new ArrayList<>();
        for (List<Order> orders : ordersByUserId.values()) {
            allOrders.addAll(orders);
        }
        return allOrders;
    }
}
