package org.example.movieBooking;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MovieBookingDemo {

    public static void main(String[] args) throws InterruptedException {
        Stores stores = new Stores();
        CatalogService catalog = new CatalogService(stores);
        SearchService search = new SearchService(stores);
        BookingService booking = new BookingService(stores, new MockPaymentGateway());

        // Admin setup
        String m1 = catalog.addMovie("Inception", "English", 148);
        String th1 = catalog.addTheater("PVR Koramangala", "Bangalore");
        String sc1 = catalog.addScreen(th1, "Screen-1", 5, 8); // 5 rows, 8 seats/row
        String show1 = catalog.addShow(m1, th1, sc1, Instant.now().plusSeconds(3600), 250.0, 400.0);

        // Browse
        System.out.println("Movies in Bangalore: " + titles(search.listMoviesByCity("Bangalore")));
        System.out.println("Shows for Inception: " + showIds(search.listShows(m1, "Bangalore")));
        System.out.println("Initial seating (subset): " + search.getSeating(show1).entrySet().stream().limit(10).toList());

        // Concurrent booking simulation (two users eye same seats)
        List<String> targetSeats = List.of("A1", "A2", "A3"); // premium row
        Runnable r1 = () -> {
            try {
                Booking b = booking.book("user-1", show1, targetSeats);
                System.out.println("[user-1] BOOKED: " + b.seatIds + " total=" + b.totalAmount);
            } catch (Exception e) {
                System.out.println("[user-1] FAILED: " + e.getMessage());
            }
        };
        Runnable r2 = () -> {
            try {
                Booking b = booking.book("user-2", show1, targetSeats);
                System.out.println("[user-2] BOOKED: " + b.seatIds + " total=" + b.totalAmount);
            } catch (Exception e) {
                System.out.println("[user-2] FAILED: " + e.getMessage());
            }
        };

        ExecutorService ex = Executors.newFixedThreadPool(2);
        ex.submit(r1);
        ex.submit(r2);
        ex.shutdown();
        ex.awaitTermination(3, TimeUnit.SECONDS);

        System.out.println("Post-booking seating (A1..A5): " + search.getSeating(show1).entrySet().stream()
                .filter(e -> e.getKey().matches("A[1-5]"))
                .toList());
    }

    private static List<String> titles(List<Movie> ms) {
        List<String> t = new ArrayList<>(); for (Movie m : ms) t.add(m.title); return t;
    }
    private static List<String> showIds(List<Show> ss) {
        List<String> ids = new ArrayList<>(); for (Show s : ss) ids.add(s.id); return ids;
    }
}
