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
                if (productId == null || productId.isBlank()) {
                                throw new EcommerceException("Product ID must not be blank.");
                }
                Product product = appContext.getAppData().getProducts().get(productId);
                if (product == null) {
                                throw new EcommerceException("Product not found: " + productId);
                }
                return product;
    }

    public List<Product> listProducts() {
                return new ArrayList<>(appContext.getAppData().getProducts().values());
    }

    public List<Product> searchProducts(String query) {
                if (query == null || query.isBlank()) {
                                return listProducts();
                }
                String normalized = query.trim().toLowerCase(Locale.ROOT);
                return listProducts().stream()
                                    .filter(p -> p.getName().toLowerCase(Locale.ROOT).contains(normalized)
                                                                    || p.getDescription().toLowerCase(Locale.ROOT).contains(normalized))
                                    .collect(Collectors.toList());
    }

    public List<Product> filterByCategory(String category) {
                if (category == null || category.isBlank()) {
                                return listProducts();
                }
                String normalized = category.trim().toLowerCase(Locale.ROOT);
                return listProducts().stream()
                                    .filter(p -> p.getCategory().toLowerCase(Locale.ROOT).equals(normalized))
                                    .collect(Collectors.toList());
    }

    public void updateInventory(String productId, int quantityChange) {
                Product product = getProductById(productId);
                int updatedQuantity = product.getQuantity() + quantityChange;
                if (updatedQuantity < 0) {
                                throw new EcommerceException("Insufficient stock. Available: " + product.getQuantity());
                }
                product.setQuantity(updatedQuantity);
                appContext.save();
    }

    /**
     * Update editable fields of an existing product.
         * Pass null for any field to leave it unchanged.
         */
    public Product updateProduct(String productId, String name, Double price, String description,
                                                                   Integer quantity, String category) {
                Product product = getProductById(productId);
                if (name != null && !name.isBlank()) {
                                product.setName(name);
                }
                if (price != null) {
                                if (price < 0) {
                                                    throw new EcommerceException("Price must not be negative.");
                                }
                                product.setPrice(price);
                }
                if (description != null) {
                                product.setDescription(description);
                }
                if (quantity != null) {
                                if (quantity < 0) {
                                                    throw new EcommerceException("Quantity must not be negative.");
                                }
                                product.setQuantity(quantity);
                }
                if (category != null && !category.isBlank()) {
                                product.setCategory(category);
                }
                appContext.save();
                return product;
    }

    private void validateProductInput(String name, double price, int quantity, String category) {
                if (name == null || name.isBlank()) {
                                throw new EcommerceException("Product name must not be blank.");
                }
                if (price < 0) {
                                throw new EcommerceException("Price must not be negative.");
                }
                if (quantity < 0) {
                                throw new EcommerceException("Quantity must not be negative.");
                }
                if (category == null || category.isBlank()) {
                                throw new EcommerceException("Product category must not be blank.");
                }
    }
}
