package com.jaideep.ecommerce.services;

import com.jaideep.ecommerce.exceptions.EcommerceException;
import com.jaideep.ecommerce.models.Product;
import com.jaideep.ecommerce.utils.IdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ProductService {
    private final AppContext appContext;

    public ProductService(AppContext appContext) {
        this.appContext = appContext;
    }

    public Product addProduct(String name, double price, String description, int quantity, String category) {
        validateProductInput(name, price, quantity, category);
        Product product = new Product(IdGenerator.newId("PRD"), name, price, description, quantity, category);
        appContext.getAppData().getProducts().put(product.getId(), product);
        appContext.save();
        return product;
    }

    public void removeProduct(String productId) {
        if (appContext.getAppData().getProducts().remove(productId) == null) {
            throw new EcommerceException("Product not found.");
        }
        appContext.save();
    }

    public Product getProductById(String productId) {
        Product product = appContext.getAppData().getProducts().get(productId);
        if (product == null) {
            throw new EcommerceException("Product not found.");
        }
        return product;
    }

    public List<Product> listProducts() {
        return new ArrayList<>(appContext.getAppData().getProducts().values());
    }

    public List<Product> searchProducts(String keyword) {
        String normalized = keyword == null ? "" : keyword.toLowerCase(Locale.ROOT);
        return listProducts().stream()
                .filter(p -> p.getName().toLowerCase(Locale.ROOT).contains(normalized)
                        || p.getDescription().toLowerCase(Locale.ROOT).contains(normalized))
                .collect(Collectors.toList());
    }

    public List<Product> filterByCategory(String category) {
        String normalized = category == null ? "" : category.toLowerCase(Locale.ROOT);
        return listProducts().stream()
                .filter(p -> p.getCategory().toLowerCase(Locale.ROOT).equals(normalized))
                .collect(Collectors.toList());
    }

    public void updateInventory(String productId, int quantityChange) {
        Product product = getProductById(productId);
        int updatedQuantity = product.getQuantity() + quantityChange;
        if (updatedQuantity < 0) {
            throw new EcommerceException("Insufficient stock.");
        }
        product.setQuantity(updatedQuantity);
        appContext.save();
    }

    private void validateProductInput(String name, double price, int quantity, String category) {
        if (name == null || name.isBlank() || category == null || category.isBlank()) {
            throw new EcommerceException("Product name and category are required.");
        }
        if (price < 0 || quantity < 0) {
            throw new EcommerceException("Price and quantity must be non-negative.");
        }
    }
}
