package org.example.carRentalSystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SearchService {

    private final CarRepository cars;
    private final ReservationRepository reservations;

    SearchService(CarRepository cars, ReservationRepository reservations) {
        this.cars = cars; this.reservations = reservations;
    }

    public List<Car> search(CarType type, double minPrice, double maxPrice,
                            LocalDate start, LocalDate end) {
        List<Car> result = new ArrayList<>();
        for (Car c : cars.all()) {
            if (c.getStatus() != CarStatus.AVAILABLE) continue;
            if (type != null && c.getType() != type) continue;
            if (c.getPricePerDay() < minPrice || c.getPricePerDay() > maxPrice) continue;
            if (isAvailable(c.getId(), start, end)) result.add(c);
        }
        return result;
    }

    public boolean isAvailable(String carId, LocalDate start, LocalDate end) {
        for (Reservation r : reservations.findByCar(carId)) {
            if (r.getStatus() == ReservationStatus.ACTIVE) {
                if (datesOverlap(r.getStartDate(), r.getEndDate(), start, end)) return false;
            }
        }
        return true;
    }

    private boolean datesOverlap(LocalDate aStart, LocalDate aEnd, LocalDate bStart, LocalDate bEnd) {
        return !(aEnd.isBefore(bStart) || bEnd.isBefore(aStart));
    }
}
