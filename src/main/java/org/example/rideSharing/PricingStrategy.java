package org.example.rideSharing;

public interface PricingStrategy {
    double calculateFare(Location pickup, Location dropoff, RideType rideType);
}