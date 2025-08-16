package org.example.carRentalSystem;

import java.time.LocalDate;

public class Reservation {

    private final String id;
    private final String carId;
    private final String customerId;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final double totalPrice;
    private ReservationStatus status;

    Reservation(String id, String carId, String customerId, LocalDate start, LocalDate end, double total, ReservationStatus status) {
        this.id = id;
        this.carId = carId;
        this.customerId = customerId;
        this.startDate = start;
        this.endDate = end;
        this.totalPrice = total;
        this.status = status;
    }

    public String getId() { return id; }
    public String getCarId() { return carId; }
    public String getCustomerId() { return customerId; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public double getTotalPrice() { return totalPrice; }
    public ReservationStatus getStatus() { return status; }
    public void setStatus(ReservationStatus s) { this.status = s; }


    @Override public String toString() {
        return "Reservation{" + id + ", car=" + carId + ", cust=" + customerId +
                ", " + startDate + "→" + endDate + ", ₹" + totalPrice + ", " + status + "}";
    }
}
