package org.example.movieBooking;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Show {

    final String id;
    final Movie movie;
    final Theater theater;
    final Screen screen;
    final Instant startTime;
    final Map<SeatType, Double> pricing = new ConcurrentHashMap<>();

    // Per-show seat state & locks
    final Map<String, SeatStatus> seatStatus = new ConcurrentHashMap<>();
    final Map<String, ReentrantLock> seatLocks = new ConcurrentHashMap<>();

    Show(String id, Movie movie, Theater theater, Screen screen, Instant startTime) {
        this.id = id;
        this.movie = movie;
        this.theater = theater;
        this.screen = screen;
        this.startTime = startTime;
        // initialize seat state + locks
        for (Seat s : screen.seats) {
            seatStatus.put(s.id, SeatStatus.AVAILABLE);
            seatLocks.put(s.id, new ReentrantLock());
        }
    }
}
