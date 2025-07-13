package org.example.parkingLot;

public class HourlyFeeStrategy implements FeeCalculationStrategy {

    @Override
    public double calculateFees(long duration) {
        double hours = Math.ceil(duration / (1000.0 * 60 * 60));
        return hours * 20;
    }
}
