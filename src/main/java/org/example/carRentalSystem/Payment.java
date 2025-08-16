package org.example.carRentalSystem;

public interface Payment {

    void charge(String customerId, double amount);

}
