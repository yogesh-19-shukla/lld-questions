package org.example.onlineShopping;

public class PaymentService {
    public boolean processPayment(PaymentStrategy strategy, double amount) {
        return strategy.pay(amount);
    }
}