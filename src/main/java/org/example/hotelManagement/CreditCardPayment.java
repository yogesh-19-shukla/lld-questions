package org.example.hotelManagement;

public class CreditCardPayment implements Payment {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing credit card payment!");
        return true;
    }
}
