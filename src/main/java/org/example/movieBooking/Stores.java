package org.example.movieBooking;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Stores {

    final Map<String, Movie> movies = new ConcurrentHashMap<>();
    final Map<String, Theater> theaters = new ConcurrentHashMap<>();
    final Map<String, Show> shows = new ConcurrentHashMap<>();
    final Map<String, Booking> bookings = new ConcurrentHashMap<>();
}
