package org.example.movieBooking;

public class MockPaymentGateway implements PaymentGateway {

    public boolean charge(String userId, double amount) {
        // simulate success (could randomize failures)
        return true;
    }
}
