package com.jaideep.ecommerce.services;

import com.jaideep.ecommerce.models.Order;
import com.jaideep.ecommerce.models.OrderStatus;
import com.jaideep.ecommerce.models.Payment;
import com.jaideep.ecommerce.models.PaymentMethod;
import com.jaideep.ecommerce.models.PaymentStatus;
import com.jaideep.ecommerce.models.User;
import com.jaideep.ecommerce.utils.IdGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {
    private final AppContext appContext;

    public PaymentService(AppContext appContext) {
        this.appContext = appContext;
    }

    public Payment processPayment(User user, Order order, PaymentMethod method) {
        Payment payment = PaymentFactory.createPayment(user, order, method);
        appContext.getAppData().getPaymentsByUserId()
                .computeIfAbsent(user.getId(), k -> new ArrayList<>())
                .add(payment);
        if (payment.getStatus() == PaymentStatus.SUCCESS) {
            order.setStatus(OrderStatus.CONFIRMED);
        }
        appContext.save();
        return payment;
    }

    public List<Payment> getTransactionHistory(User user) {
        return new ArrayList<>(appContext.getAppData().getPaymentsByUserId().getOrDefault(user.getId(), new ArrayList<>()));
    }

    private static class PaymentFactory {
        private static Payment createPayment(User user, Order order, PaymentMethod method) {
            PaymentStatus paymentStatus = method == PaymentMethod.CASH_ON_DELIVERY
                    ? PaymentStatus.PENDING
                    : PaymentStatus.SUCCESS;
            return new Payment(
                    IdGenerator.newId("PAY"),
                    order.getId(),
                    user.getId(),
                    method,
                    order.getTotalAmount(),
                    paymentStatus,
                    LocalDateTime.now()
            );
        }
    }
}
