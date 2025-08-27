package org.example.movieBooking;

import java.time.Instant;
import java.util.UUID;

public class CatalogService {

    private final Stores stores;

    CatalogService(Stores stores) {
        this.stores = stores;
    }

    public String addMovie(String title, String lang, int mins) {
        String id = "m-" + UUID.randomUUID();
        stores.movies.put(id, new Movie(id, title, lang, mins));
        return id;
    }

    public String addTheater(String name, String city) {
        String id = "t-" + UUID.randomUUID();
        stores.theaters.put(id, new Theater(id, name, city));
        return id;
    }

    public String addScreen(String theaterId, String name, int rows, int seatsPerRow) {
        Theater th = stores.theaters.get(theaterId);
        if (th == null) throw new IllegalArgumentException("No theater");
        String id = "s-" + UUID.randomUUID();
        Screen sc = new Screen(id, name);
        // build seats (first rows premium)
        for (int r = 0; r < rows; r++) {
            char rowChar = (char)('A' + r);
            SeatType type = (r < Math.max(1, rows/3)) ? SeatType.PREMIUM : SeatType.NORMAL;
            for (int n = 1; n <= seatsPerRow; n++) {
                String seatId = rowChar + String.valueOf(n);
                sc.seats.add(new Seat(seatId, String.valueOf(rowChar), n, type));
            }
        }
        th.screens.put(id, sc);
        return id;
    }

    public String addShow(String movieId, String theaterId, String screenId, Instant start, double normalPrice, double premiumPrice) {
        Movie m = stores.movies.get(movieId);
        Theater th = stores.theaters.get(theaterId);
        if (m == null || th == null) throw new IllegalArgumentException("Missing refs");
        Screen sc = th.screens.get(screenId);
        if (sc == null) throw new IllegalArgumentException("No screen");
        String showId = "sh-" + UUID.randomUUID();
        Show sh = new Show(showId, m, th, sc, start);
        sh.pricing.put(SeatType.NORMAL, normalPrice);
        sh.pricing.put(SeatType.PREMIUM, premiumPrice);
        stores.shows.put(showId, sh);
        return showId;
    }

    // Admin ops - remove/update (stubs)
    public void removeShow(String showId) { stores.shows.remove(showId); }
}
