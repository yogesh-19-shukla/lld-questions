package org.example.movieBooking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class BookingService {

    private final Stores stores;
    private final PaymentGateway pg;
    private final AtomicLong seq = new AtomicLong(0);

    BookingService(Stores stores, PaymentGateway pg) {
        this.stores = stores; this.pg = pg;
    }

    public Booking book(String userId, String showId, List<String> requestedSeats) {
        Show sh = stores.shows.get(showId);
        if (sh == null) throw new IllegalArgumentException("Show not found");

        // 1) Sort to ensure a global lock order (avoid deadlocks)
        List<String> seats = new ArrayList<>(requestedSeats);
        Collections.sort(seats);

        // 2) Acquire locks in order
        List<ReentrantLock> acquired = new ArrayList<>();
        try {
            for (String sid : seats) {
                ReentrantLock lock = sh.seatLocks.get(sid);
                if (lock == null) throw new IllegalArgumentException("Seat " + sid + " not found");
                lock.lock(); // could use tryLock with timeout
                acquired.add(lock);
                // validate availability
                if (sh.seatStatus.get(sid) != SeatStatus.AVAILABLE) {
                    throw new IllegalStateException("Seat " + sid + " not available");
                }
            }

            // 3) All available: mark HELD (optional; immediate)
            for (String sid : seats) sh.seatStatus.put(sid, SeatStatus.HELD);

            // 4) Compute price & charge
            double total = 0.0;
            for (String sid : seats) {
                SeatType type = sh.screen.seats.stream().filter(s -> s.id.equals(sid)).findFirst().get().type;
                total += sh.pricing.get(type);
            }
            boolean paid = pg.charge(userId, total);
            if (!paid) throw new RuntimeException("Payment failed");

            // 5) Mark BOOKED
            for (String sid : seats) sh.seatStatus.put(sid, SeatStatus.BOOKED);

            // 6) Create booking
            String bid = "b-" + seq.incrementAndGet();
            Booking b = new Booking(bid, userId, showId, seats, total, BookingStatus.CONFIRMED);
            stores.bookings.put(bid, b);
            return b;

        } catch (RuntimeException e) {
            // rollback HELD → AVAILABLE if any
            for (String sid : seats) {
                if (sh.seatStatus.get(sid) == SeatStatus.HELD) {
                    sh.seatStatus.put(sid, SeatStatus.AVAILABLE);
                }
            }
            throw e;
        } finally {
            // 7) Release locks
            for (ReentrantLock l : acquired) l.unlock();
        }
    }
}
