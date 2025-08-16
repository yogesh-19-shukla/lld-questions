package org.example.carRentalSystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ReservationRepository {

    private final Map<String, Reservation> byId = new ConcurrentHashMap<>();
    private final Map<String, List<Reservation>> byCar = new ConcurrentHashMap<>();

    public void add(Reservation r) {
        byId.put(r.getId(), r);
        byCar.computeIfAbsent(r.getCarId(), k -> Collections.synchronizedList(new ArrayList<>())).add(r);
    }

    public Reservation get(String id) { return byId.get(id); }

    public List<Reservation> findByCar(String carId) {
        return byCar.getOrDefault(carId, Collections.emptyList());
    }

    public void update(Reservation r) { byId.put(r.getId(), r); }

    public void remove(String id) {
        Reservation r = byId.remove(id);
        if (r != null) {
            List<Reservation> list = byCar.get(r.getCarId());
            if (list != null) list.removeIf(x -> x.getId().equals(id));
        }
    }
}
