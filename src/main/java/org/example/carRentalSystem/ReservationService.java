package org.example.carRentalSystem;

import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class ReservationService {

    private final CarRepository cars;
    private final CustomerRepository customers;
    private final ReservationRepository reservations;
    private final SearchService search;
    private final Payment payment;

    // per-car locks to prevent double-booking
    private final ConcurrentHashMap<String, ReentrantLock> carLocks = new ConcurrentHashMap<>();

    ReservationService(CarRepository cars, CustomerRepository customers,
                       ReservationRepository reservations, SearchService search, Payment payment) {
        this.cars = cars; this.customers = customers; this.reservations = reservations; this.search = search; this.payment = payment;
    }

    private ReentrantLock lockFor(String carId) {
        return carLocks.computeIfAbsent(carId, k -> new ReentrantLock());
    }

    public Reservation create(String customerId, String carId, LocalDate start, LocalDate end) {
        validateInputs(customerId, carId, start, end);
        ReentrantLock lock = lockFor(carId);
        lock.lock();
        try {
            if (!search.isAvailable(carId, start, end)) {
                System.out.println("[RESERVE] Car " + carId + " NOT available for " + start + "→" + end);
                return null;
            }
            Car car = cars.get(carId);
            long days = end.toEpochDay() - start.toEpochDay() + 1;
            double total = car.getPricePerDay() * days;
            payment.charge(customerId, total);
            Reservation r = new Reservation(UUID.randomUUID().toString(), carId, customerId, start, end, total, ReservationStatus.ACTIVE);
            reservations.add(r);
            System.out.println("[RESERVE] SUCCESS " + r);
            return r;
        } finally {
            lock.unlock();
        }
    }

    public boolean cancel(String reservationId) {
        Reservation r = reservations.get(reservationId);
        if (r == null) return false;
        ReentrantLock lock = lockFor(r.getCarId());
        lock.lock();
        try {
            if (r.getStatus() != ReservationStatus.ACTIVE) return false;
            r.setStatus(ReservationStatus.CANCELLED);
            reservations.update(r);
            System.out.println("[CANCEL] " + reservationId + " cancelled");
            return true;
        } finally {
            lock.unlock();
        }
    }

    // Modify: change dates OR even change car
    public Reservation modify(String reservationId, String newCarId, LocalDate newStart, LocalDate newEnd) {
        Reservation old = reservations.get(reservationId);
        if (old == null || old.getStatus() != ReservationStatus.ACTIVE) return null;

        String carA = old.getCarId();
        String carB = (newCarId == null) ? carA : newCarId;

        // lock ordering to avoid deadlock
        String first = carA.compareTo(carB) <= 0 ? carA : carB;
        String second = carA.compareTo(carB) <= 0 ? carB : carA;

        ReentrantLock lock1 = lockFor(first);
        ReentrantLock lock2 = lockFor(second);
        lock1.lock();
        lock2.lock();
        try {
            // re-check availability on target car
            String targetCar = carB;
            if (!search.isAvailable(targetCar, newStart, newEnd)) {
                System.out.println("[MODIFY] target car not available " + targetCar);
                return null;
            }
            // mark old as cancelled
            old.setStatus(ReservationStatus.CANCELLED);
            reservations.update(old);

            // create new reservation
            String customerId = old.getCustomerId();
            Car car = cars.get(targetCar);
            long days = newEnd.toEpochDay() - newStart.toEpochDay() + 1;
            double total = car.getPricePerDay() * days;
            payment.charge(customerId, Math.max(0, total - old.getTotalPrice())); // simplistic delta charge
            Reservation fresh = new Reservation(UUID.randomUUID().toString(), targetCar, customerId, newStart, newEnd, total, ReservationStatus.ACTIVE);
            reservations.add(fresh);
            System.out.println("[MODIFY] SUCCESS " + fresh + " (old " + old.getId() + " cancelled)");
            return fresh;
        } finally {
            lock2.unlock();
            lock1.unlock();
        }
    }

    private void validateInputs(String customerId, String carId, LocalDate start, LocalDate end) {
        if (customers.get(customerId) == null) throw new IllegalArgumentException("Unknown customer");
        if (cars.get(carId) == null) throw new IllegalArgumentException("Unknown car");
        if (start == null || end == null || end.isBefore(start)) throw new IllegalArgumentException("Bad dates");
    }
}
