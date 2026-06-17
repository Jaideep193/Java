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
                                throw new EcommerceException(
                                                        "Insufficient stock. Requested: " + quantity + ", available: " + product.getQuantity());
                }
                List<CartItem> cart = getOrCreateCart(user.getId());
                for (CartItem item : cart) {
                                if (item.getProductId().equals(productId)) {
                                                    int updatedQty = item.getQuantity() + quantity;
                                                    if (updatedQty > product.getQuantity()) {
                                                                            throw new EcommerceException(
                                                                                                            "Quantity exceeds available stock. Available: " + product.getQuantity()
                                                                                                                    + ", already in cart: " + item.getQuantity());
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

    public void updateItemQuantity(User user, String productId, int newQuantity) {
                if (newQuantity < 0) {
                                throw new EcommerceException("Quantity must not be negative.");
                }
                if (newQuantity == 0) {
                                removeItem(user, productId);
                                return;
                }
                Product product = productService.getProductById(productId);
                if (newQuantity > product.getQuantity()) {
                                throw new EcommerceException(
                                                        "Quantity exceeds available stock. Available: " + product.getQuantity());
                }
                List<CartItem> cart = getOrCreateCart(user.getId());
                for (CartItem item : cart) {
                                if (item.getProductId().equals(productId)) {
                                                    item.setQuantity(newQuantity);
                                                    appContext.save();
                                                    return;
                                }
                }
                throw new EcommerceException("Item not present in cart.");
    }

    public List<CartItem> getCart(User user) {
                return getOrCreateCart(user.getId());
    }

    public double getTotal(User user) {
                double total = 0;
                for (CartItem item : getOrCreateCart(user.getId())) {
                                Product product = productService.getProductById(item.getProductId());
                                total += item.getQuantity() * product.getPrice();
                }
                return total;
    }

    /** Returns the total number of individual items (units) in the cart. */
    public int getItemCount(User user) {
                return getOrCreateCart(user.getId()).stream()
                                    .mapToInt(CartItem::getQuantity)
                                    .sum();
    }

    public void clearCart(String userId) {
                getOrCreateCart(userId).clear();
                appContext.save();
    }

    private List<CartItem> getOrCreateCart(String userId) {
                return appContext.getAppData().getCarts()
                                    .computeIfAbsent(userId, k -> new ArrayList<>());
    }
}
