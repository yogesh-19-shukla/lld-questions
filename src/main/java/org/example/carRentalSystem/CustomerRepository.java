package org.example.carRentalSystem;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CustomerRepository {

    private final Map<String, Customer> customers = new ConcurrentHashMap<>();
    public void add(Customer c) { customers.put(c.getId(), c); }
    public Customer get(String id) { return customers.get(id); }
}
