package org.example.movieBooking;

import java.util.*;

public class SearchService {

    private final Stores stores;

    SearchService(Stores stores) {
        this.stores = stores;
    }

    public List<Movie> listMoviesByCity(String city) {
        // movies that have at least one show in city
        Set<String> movieIds = new HashSet<>();
        for (Show sh : stores.shows.values()) {
            if (sh.theater.city.equalsIgnoreCase(city)) movieIds.add(sh.movie.id);
        }
        List<Movie> res = new ArrayList<>();
        for (String id : movieIds) res.add(stores.movies.get(id));
        return res;
    }

    public List<Show> listShows(String movieId, String city) {
        List<Show> res = new ArrayList<>();
        for (Show sh : stores.shows.values()) {
            if (sh.movie.id.equals(movieId) && sh.theater.city.equalsIgnoreCase(city)) res.add(sh);
        }
        return res;
    }

    public Map<String, SeatStatus> getSeating(String showId) {
        Show sh = stores.shows.get(showId);
        if (sh == null) throw new IllegalArgumentException("Show not found");
        return new TreeMap<>(sh.seatStatus); // ordered by seat id just for display
    }
}
