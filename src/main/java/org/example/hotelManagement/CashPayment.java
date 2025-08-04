package org.example.hotelManagement;

public class CashPayment implements Payment {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing cash payment!");
        return true;
    }
}
