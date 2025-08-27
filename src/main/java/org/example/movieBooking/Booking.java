package org.example.movieBooking;

import java.util.List;

public class Booking {

    final String id;
    final String userId;
    final String showId;
    final List<String> seatIds;
    final double totalAmount;
    BookingStatus status;


    Booking(String id, String userId, String showId, List<String> seatIds, double amt, BookingStatus status) {
        this.id = id;
        this.userId = userId;
        this.showId = showId;
        this.seatIds = seatIds;
        this.totalAmount = amt;
        this.status = status;
    }
}
