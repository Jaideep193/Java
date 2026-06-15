package com.jaideep.ecommerce.services;

import com.jaideep.ecommerce.exceptions.EcommerceException;
import com.jaideep.ecommerce.models.CartItem;
import com.jaideep.ecommerce.models.Order;
import com.jaideep.ecommerce.models.OrderItem;
import com.jaideep.ecommerce.models.OrderStatus;
import com.jaideep.ecommerce.models.Product;
import com.jaideep.ecommerce.models.User;
import com.jaideep.ecommerce.utils.IdGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private final AppContext appContext;
    private final ProductService productService;
    private final CartService cartService;

    public OrderService(AppContext appContext, ProductService productService, CartService cartService) {
        this.appContext = appContext;
        this.productService = productService;
        this.cartService = cartService;
    }

    public Order placeOrder(User user) {
        List<CartItem> cartItems = cartService.viewCart(user);
        if (cartItems.isEmpty()) {
            throw new EcommerceException("Cart is empty.");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0;

        for (CartItem item : cartItems) {
            Product product = productService.getProductById(item.getProductId());
            if (product.getQuantity() < item.getQuantity()) {
                throw new EcommerceException("Insufficient stock for product: " + product.getName());
            }
        }

        for (CartItem item : cartItems) {
            Product product = productService.getProductById(item.getProductId());
            productService.updateInventory(product.getId(), -item.getQuantity());
            OrderItem orderItem = new OrderItem(product.getId(), product.getName(), item.getQuantity(), product.getPrice());
            orderItems.add(orderItem);
            total += orderItem.getSubtotal();
        }

        Order order = new Order(IdGenerator.newId("ORD"), user.getId(), orderItems, total, LocalDateTime.now(), OrderStatus.PLACED);
        appContext.getAppData().getOrdersByUserId()
                .computeIfAbsent(user.getId(), k -> new ArrayList<>())
                .add(order);

        cartService.clearCart(user.getId());
        appContext.save();
        return order;
    }

    public List<Order> getOrderHistory(User user) {
        return new ArrayList<>(appContext.getAppData().getOrdersByUserId().getOrDefault(user.getId(), new ArrayList<>()));
    }

    public void updateOrderStatus(String userId, String orderId, OrderStatus orderStatus) {
        Order order = findOrderById(userId, orderId);
        order.setStatus(orderStatus);
        appContext.save();
    }

    public Order findOrderById(String userId, String orderId) {
        for (Order order : appContext.getAppData().getOrdersByUserId().getOrDefault(userId, new ArrayList<>())) {
            if (order.getId().equals(orderId)) {
                return order;
            }
        }
        throw new EcommerceException("Order not found.");
    }

    public String generateInvoice(Order order) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n--------- INVOICE ---------\n")
                .append("Order ID: ").append(order.getId()).append("\n")
                .append("Status  : ").append(order.getStatus()).append("\n")
                .append("Date    : ").append(order.getCreatedAt()).append("\n")
                .append("Items:\n");
        for (OrderItem item : order.getItems()) {
            builder.append("- ").append(item.getProductName())
                    .append(" x ").append(item.getQuantity())
                    .append(" @ ").append(item.getUnitPrice())
                    .append(" = ").append(item.getSubtotal()).append("\n");
        }
        builder.append("Total: ").append(order.getTotalAmount()).append("\n")
                .append("---------------------------\n");
        return builder.toString();
    }
}
