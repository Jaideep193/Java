package com.jaideep.ecommerce.utils;

import com.jaideep.ecommerce.models.Role;
import com.jaideep.ecommerce.services.AuthService;
import com.jaideep.ecommerce.services.ProductService;

public final class SampleDataInitializer {
    private SampleDataInitializer() {
    }

    public static void initialize(AuthService authService, ProductService productService) {
        String adminPassword = System.getenv().getOrDefault("ECOM_ADMIN_PASSWORD", "ChangeMe#2026");
        if (productService.listProducts().isEmpty()) {
            productService.addProduct("Laptop", 75000, "15-inch productivity laptop", 10, "Electronics");
            productService.addProduct("Wireless Mouse", 1200, "Ergonomic wireless mouse", 30, "Accessories");
            productService.addProduct("Running Shoes", 2999, "Lightweight running shoes", 20, "Fashion");
        }
        try {
            authService.register("admin", adminPassword, "admin@store.com", "HQ", Role.ADMIN);
        } catch (Exception ignored) {
            // May already exist in persisted data.
        }
        try {
            authService.register("demo", "demo123", "demo@store.com", "Demo Address", Role.CUSTOMER);
        } catch (Exception ignored) {
            // May already exist in persisted data.
        }
    }
}
