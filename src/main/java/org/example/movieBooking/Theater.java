package org.example.movieBooking;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Theater {

    final String id, name, city;
    final Map<String, Screen> screens = new ConcurrentHashMap<>();

    Theater(String id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }
}
