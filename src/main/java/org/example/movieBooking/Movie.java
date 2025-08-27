package org.example.movieBooking;

public class Movie {

    final String id, title, language;
    final int durationMins;
    Movie(String id, String title, String language, int durationMins) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.durationMins = durationMins;
    }
}
