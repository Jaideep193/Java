package com.jaideep.ecommerce.ui;

import com.jaideep.ecommerce.controller.AppController;
import com.jaideep.ecommerce.exceptions.EcommerceException;
import com.jaideep.ecommerce.models.CartItem;
import com.jaideep.ecommerce.models.Order;
import com.jaideep.ecommerce.models.Payment;
import com.jaideep.ecommerce.models.PaymentMethod;
import com.jaideep.ecommerce.models.Product;
import com.jaideep.ecommerce.models.Role;
import com.jaideep.ecommerce.models.User;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final AppController appController;
    private final Scanner scanner;
    private User currentUser;

    public ConsoleUI(AppController appController) {
        this.appController = appController;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            try {
                if (currentUser == null) {
                    showAuthMenu();
                } else if (currentUser.getRole() == Role.ADMIN) {
                    showAdminMenu();
                } else {
                    showCustomerMenu();
                }
            } catch (EcommerceException exception) {
                System.out.println("Error: " + exception.getMessage());
            }
        }
    }

    private void showAuthMenu() {
        System.out.println("\n--- E-Commerce App ---");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("0. Exit");
        int choice = readInt("Choose: ");

        switch (choice) {
            case 1 -> register();
            case 2 -> login();
            case 0 -> {
                System.out.println("Goodbye!");
                System.exit(0);
            }
            default -> System.out.println("Invalid choice.");
        }
    }

    private void register() {
        String username = readLine("Username: ");
        String password = readLine("Password: ");
        String email = readLine("Email: ");
        String address = readLine("Address: ");
        appController.register(username, password, email, address, Role.CUSTOMER);
        System.out.println("Registration successful. Please login.");
    }

    private void login() {
        String username = readLine("Username: ");
        String password = readLine("Password: ");
        currentUser = appController.login(username, password);
        System.out.println("Welcome, " + currentUser.getUsername() + "!");
    }

    private void showCustomerMenu() {
        System.out.println("\n--- Customer Menu ---");
        System.out.println("1. View Products");
        System.out.println("2. Search Products");
        System.out.println("3. Filter by Category");
        System.out.println("4. Add to Cart");
        System.out.println("5. View Cart");
        System.out.println("6. Update Cart Quantity");
        System.out.println("7. Remove from Cart");
        System.out.println("8. Place Order");
        System.out.println("9. View Order History");
        System.out.println("10. View Transactions");
        System.out.println("11. Update Profile");
        System.out.println("12. Logout");

        int choice = readInt("Choose: ");
        switch (choice) {
            case 1 -> printProducts(appController.listProducts());
            case 2 -> printProducts(appController.searchProducts(readLine("Keyword: ")));
            case 3 -> printProducts(appController.filterProducts(readLine("Category: ")));
            case 4 -> appController.addToCart(currentUser, readLine("Product ID: "), readInt("Quantity: "));
            case 5 -> viewCart();
            case 6 -> appController.updateCart(currentUser, readLine("Product ID: "), readInt("New Quantity: "));
            case 7 -> appController.removeFromCart(currentUser, readLine("Product ID: "));
            case 8 -> placeAndPay();
            case 9 -> viewOrderHistory();
            case 10 -> viewTransactions();
            case 11 -> updateProfile();
            case 12 -> currentUser = null;
            default -> System.out.println("Invalid choice.");
        }
    }

    private void showAdminMenu() {
        System.out.println("\n--- Admin Menu ---");
        System.out.println("1. View Products");
        System.out.println("2. Add Product");
        System.out.println("3. Remove Product");
        System.out.println("4. Manage Inventory");
        System.out.println("5. View All Orders");
        System.out.println("6. Generate Sales Report");
        System.out.println("7. Logout");

        int choice = readInt("Choose: ");
        switch (choice) {
            case 1 -> printProducts(appController.listProducts());
            case 2 -> addProduct();
            case 3 -> appController.removeProduct(readLine("Product ID: "));
            case 4 -> appController.updateInventory(readLine("Product ID: "), readInt("Quantity change (+/-): "));
            case 5 -> printOrders(appController.allOrders());
            case 6 -> System.out.println(appController.salesReport());
            case 7 -> currentUser = null;
            default -> System.out.println("Invalid choice.");
        }
    }

    private void addProduct() {
        appController.addProduct(
                readLine("Name: "),
                readDouble("Price: "),
                readLine("Description: "),
                readInt("Quantity: "),
                readLine("Category: ")
        );
        System.out.println("Product added.");
    }

    private void placeAndPay() {
        Order order = appController.placeOrder(currentUser);
        System.out.println("Order placed: " + order.getId());
        System.out.println("Select Payment Method: 1.CARD 2.UPI 3.NET_BANKING 4.COD");
        int methodChoice = readInt("Choice: ");
        PaymentMethod paymentMethod = switch (methodChoice) {
            case 1 -> PaymentMethod.CARD;
            case 2 -> PaymentMethod.UPI;
            case 3 -> PaymentMethod.NET_BANKING;
            default -> PaymentMethod.CASH_ON_DELIVERY;
        };
        Payment payment = appController.pay(currentUser, order, paymentMethod);
        System.out.println("Payment status: " + payment.getStatus() + ". Transaction ID: " + payment.getId());
        System.out.println(appController.invoice(order));
    }

    private void viewCart() {
        List<CartItem> cart = appController.viewCart(currentUser);
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        System.out.println("\n--- Cart ---");
        for (CartItem item : cart) {
            System.out.println(item.getProductId() + " x " + item.getQuantity());
        }
        System.out.println("Total: " + appController.cartTotal(currentUser));
    }

    private void viewOrderHistory() {
        List<Order> orders = appController.orderHistory(currentUser);
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }
        printOrders(orders);
    }

    private void viewTransactions() {
        List<Payment> payments = appController.transactions(currentUser);
        if (payments.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        for (Payment payment : payments) {
            System.out.println(payment.getId() + " | order=" + payment.getOrderId() + " | amount="
                    + payment.getAmount() + " | method=" + payment.getMethod() + " | " + payment.getStatus());
        }
    }

    private void updateProfile() {
        String email = readLine("New email (leave blank to keep): ");
        String address = readLine("New address (leave blank to keep): ");
        String password = readLine("New password (leave blank to keep): ");
        appController.updateProfile(currentUser, email, address, password);
        System.out.println("Profile updated.");
    }

    private void printProducts(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }
        for (Product product : products) {
            System.out.println(product.getId() + " | " + product.getName() + " | " + product.getCategory()
                    + " | Rs." + product.getPrice() + " | Qty: " + product.getQuantity() + " | " + product.getDescription());
        }
    }

    private void printOrders(List<Order> orders) {
        for (Order order : orders) {
            System.out.println(order.getId() + " | " + order.getStatus() + " | " + order.getTotalAmount() + " | " + order.getCreatedAt());
        }
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readLine(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer (e.g., 1, 2, 3).");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            try {
                return Double.parseDouble(readLine(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid decimal number (e.g., 99.99).");
            }
        }
    }
}
