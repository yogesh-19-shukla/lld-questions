package org.example.carRentalSystem;

public class Car {
    private final String id;
    private final String make;
    private final String model;
    private final int year;
    private final CarType type;
    private final double pricePerDay;
    private volatile CarStatus status = CarStatus.AVAILABLE;

    Car(String id, String make, String model, int year, CarType type, double pricePerDay) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.type = type;
        this.pricePerDay = pricePerDay;
    }

    public String getId() { return id; }
    public CarType getType() { return type; }
    public double getPricePerDay() { return pricePerDay; }
    public CarStatus getStatus() { return status; }
    public void setStatus(CarStatus s) { this.status = s; }
    @Override public String toString() {
        return id + " " + make + " " + model + " " + year + " " + type + " ₹" + pricePerDay + "/day";
    }
}
