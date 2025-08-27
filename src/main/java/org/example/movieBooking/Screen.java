package org.example.movieBooking;

import java.util.ArrayList;
import java.util.List;

public class Screen {

    final String id, name;
    final List<Seat> seats = new ArrayList<>();

    Screen(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
