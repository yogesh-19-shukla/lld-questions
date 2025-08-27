package org.example.movieBooking;

public class Seat {

    final String id; // e.g., "A1"
    final String row;
    final int number;
    final SeatType type;

    Seat(String id, String row, int number, SeatType type) {
        this.id = id;
        this.row = row;
        this.number = number;
        this.type = type;
    }
}
