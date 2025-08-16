package org.example.carRentalSystem;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CarRepository {

    private final Map<String, Car> cars = new ConcurrentHashMap<>();
    public void add(Car c) { cars.put(c.getId(), c); }
    public Car get(String id) { return cars.get(id); }
    public Collection<Car> all() { return cars.values(); }
}
