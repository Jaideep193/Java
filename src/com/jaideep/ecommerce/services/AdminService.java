package com.jaideep.ecommerce.services;

import com.jaideep.ecommerce.models.Order;

import java.util.ArrayList;
import java.util.List;

public class AdminService {
    private final AppContext appContext;

    public AdminService(AppContext appContext) {
        this.appContext = appContext;
    }

    public List<Order> viewAllOrders() {
        return new ArrayList<>(appContext.getAppData().getAllOrders());
    }

    public String generateSalesReport() {
        List<Order> orders = appContext.getAppData().getAllOrders();
        double revenue = orders.stream().mapToDouble(Order::getTotalAmount).sum();
        return "Total Orders: " + orders.size() + ", Revenue: " + revenue;
    }
}
