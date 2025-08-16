package org.example.carRentalSystem;

public class Customer {

    private final String id;
    private final String name;
    private final String contact;
    private final String driverLicense;

    Customer(String id, String name, String contact, String driverLicense) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.driverLicense = driverLicense;
    }

    public String getId() { return id; }
}
