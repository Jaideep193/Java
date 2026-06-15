package com.jaideep.ecommerce;

import com.jaideep.ecommerce.controller.AppController;
import com.jaideep.ecommerce.persistence.FileDatabaseLayer;
import com.jaideep.ecommerce.services.AdminService;
import com.jaideep.ecommerce.services.AppContext;
import com.jaideep.ecommerce.services.AuthService;
import com.jaideep.ecommerce.services.CartService;
import com.jaideep.ecommerce.services.OrderService;
import com.jaideep.ecommerce.services.PaymentService;
import com.jaideep.ecommerce.services.ProductService;
import com.jaideep.ecommerce.ui.ConsoleUI;
import com.jaideep.ecommerce.utils.SampleDataInitializer;

public class Main {
    public static void main(String[] args) {
        AppContext appContext = new AppContext(FileDatabaseLayer.getInstance());

        AuthService authService = new AuthService(appContext);
        ProductService productService = new ProductService(appContext);
        CartService cartService = new CartService(appContext, productService);
        OrderService orderService = new OrderService(appContext, productService, cartService);
        PaymentService paymentService = new PaymentService(appContext);
        AdminService adminService = new AdminService(appContext);

        SampleDataInitializer.initialize(authService, productService);

        AppController appController = new AppController(authService, productService, cartService, orderService, paymentService, adminService);
        new ConsoleUI(appController).start();
    }
}
