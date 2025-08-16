package org.example.carRentalSystem;

public class CreditCardPayment implements Payment {

    public void charge(String customerId, double amount) {
        // simulate payment
        System.out.println("[PAYMENT] charged customer=" + customerId + " amount=₹" + amount + " via CreditCard");
    }
}
