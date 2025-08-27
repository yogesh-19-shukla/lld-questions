package org.example.movieBooking;

public interface PaymentGateway {

    boolean charge(String userId, double amount);

}
