package com.jaideep.ecommerce.services;

import com.jaideep.ecommerce.exceptions.EcommerceException;
import com.jaideep.ecommerce.models.CartItem;
import com.jaideep.ecommerce.models.Product;
import com.jaideep.ecommerce.models.User;

import java.util.ArrayList;
import java.util.List;

public class CartService {
    private final AppContext appContext;
    private final ProductService productService;

    public CartService(AppContext appContext, ProductService productService) {
        this.appContext = appContext;
        this.productService = productService;
    }

    public void addItem(User user, String productId, int quantity) {
        if (quantity <= 0) {
            throw new EcommerceException("Quantity should be greater than zero.");
        }
        Product product = productService.getProductById(productId);
        if (product.getQuantity() < quantity) {
            throw new EcommerceException("Not enough stock for this product.");
        }
        List<CartItem> cart = getOrCreateCart(user.getId());
        for (CartItem item : cart) {
            if (item.getProductId().equals(productId)) {
                int updatedQty = item.getQuantity() + quantity;
                if (updatedQty > product.getQuantity()) {
                    throw new EcommerceException("Quantity exceeds available stock.");
                }
                item.setQuantity(updatedQty);
                appContext.save();
                return;
            }
        }
        cart.add(new CartItem(productId, quantity));
        appContext.save();
    }

    public void removeItem(User user, String productId) {
        List<CartItem> cart = getOrCreateCart(user.getId());
        boolean removed = cart.removeIf(item -> item.getProductId().equals(productId));
        if (!removed) {
            throw new EcommerceException("Item not present in cart.");
        }
        appContext.save();
    }

    public void updateItemQuantity(User user, String productId, int quantity) {
        if (quantity <= 0) {
            throw new EcommerceException("Quantity should be greater than zero.");
        }
        Product product = productService.getProductById(productId);
        if (quantity > product.getQuantity()) {
            throw new EcommerceException("Quantity exceeds available stock.");
        }
        List<CartItem> cart = getOrCreateCart(user.getId());
        for (CartItem item : cart) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(quantity);
                appContext.save();
                return;
            }
        }
        throw new EcommerceException("Item not present in cart.");
    }

    public List<CartItem> viewCart(User user) {
        return new ArrayList<>(getOrCreateCart(user.getId()));
    }

    public double getTotal(User user) {
        double total = 0;
        for (CartItem item : getOrCreateCart(user.getId())) {
            Product product = productService.getProductById(item.getProductId());
            total += item.getQuantity() * product.getPrice();
        }
        return total;
    }

    public void clearCart(String userId) {
        getOrCreateCart(userId).clear();
        appContext.save();
    }

    private List<CartItem> getOrCreateCart(String userId) {
        return appContext.getAppData().getCartsByUserId().computeIfAbsent(userId, k -> new ArrayList<>());
    }
}
