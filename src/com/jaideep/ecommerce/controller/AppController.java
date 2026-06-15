package com.jaideep.ecommerce.controller;

import com.jaideep.ecommerce.models.CartItem;
import com.jaideep.ecommerce.models.Order;
import com.jaideep.ecommerce.models.OrderStatus;
import com.jaideep.ecommerce.models.Payment;
import com.jaideep.ecommerce.models.PaymentMethod;
import com.jaideep.ecommerce.models.Product;
import com.jaideep.ecommerce.models.Role;
import com.jaideep.ecommerce.models.User;
import com.jaideep.ecommerce.services.AdminService;
import com.jaideep.ecommerce.services.AuthService;
import com.jaideep.ecommerce.services.CartService;
import com.jaideep.ecommerce.services.OrderService;
import com.jaideep.ecommerce.services.PaymentService;
import com.jaideep.ecommerce.services.ProductService;

import java.util.List;

public class AppController {
    private final AuthService authService;
    private final ProductService productService;
    private final CartService cartService;
    private final OrderService orderService;
    private final PaymentService paymentService;
    private final AdminService adminService;

    public AppController(AuthService authService, ProductService productService, CartService cartService,
                         OrderService orderService, PaymentService paymentService, AdminService adminService) {
        this.authService = authService;
        this.productService = productService;
        this.cartService = cartService;
        this.orderService = orderService;
        this.paymentService = paymentService;
        this.adminService = adminService;
    }

    public User register(String username, String password, String email, String address, Role role) {
        return authService.register(username, password, email, address, role);
    }

    public User login(String username, String password) {
        return authService.login(username, password);
    }

    public void updateProfile(User user, String email, String address, String password) {
        authService.updateProfile(user, email, address, password);
    }

    public List<Product> listProducts() { return productService.listProducts(); }
    public List<Product> searchProducts(String keyword) { return productService.searchProducts(keyword); }
    public List<Product> filterProducts(String category) { return productService.filterByCategory(category); }

    public Product addProduct(String name, double price, String description, int quantity, String category) {
        return productService.addProduct(name, price, description, quantity, category);
    }

    public void removeProduct(String productId) { productService.removeProduct(productId); }
    public void updateInventory(String productId, int delta) { productService.updateInventory(productId, delta); }

    public void addToCart(User user, String productId, int quantity) { cartService.addItem(user, productId, quantity); }
    public void removeFromCart(User user, String productId) { cartService.removeItem(user, productId); }
    public void updateCart(User user, String productId, int quantity) { cartService.updateItemQuantity(user, productId, quantity); }
    public List<CartItem> viewCart(User user) { return cartService.viewCart(user); }
    public double cartTotal(User user) { return cartService.getTotal(user); }

    public Order placeOrder(User user) { return orderService.placeOrder(user); }
    public List<Order> orderHistory(User user) { return orderService.getOrderHistory(user); }
    public void updateOrderStatus(String userId, String orderId, OrderStatus status) { orderService.updateOrderStatus(userId, orderId, status); }
    public String invoice(Order order) { return orderService.generateInvoice(order); }

    public Payment pay(User user, Order order, PaymentMethod method) { return paymentService.processPayment(user, order, method); }
    public List<Payment> transactions(User user) { return paymentService.getTransactionHistory(user); }

    public List<Order> allOrders() { return adminService.viewAllOrders(); }
    public String salesReport() { return adminService.generateSalesReport(); }
}
