package org.example.carRentalSystem;

import java.time.LocalDate;

public class CarRentalDemo {

    public static void main(String[] args) throws InterruptedException {
        CarRepository carRepo = new CarRepository();
        CustomerRepository custRepo = new CustomerRepository();
        ReservationRepository resRepo = new ReservationRepository();
        SearchService search = new SearchService(carRepo, resRepo);
        Payment payment = new CreditCardPayment();
        ReservationService reservations = new ReservationService(carRepo, custRepo, resRepo, search, payment);

        // seed data
        carRepo.add(new Car("KA01AB1234", "Toyota", "Corolla", 2020, CarType.SEDAN, 2000));
        carRepo.add(new Car("KA02CD5678", "Hyundai", "Creta", 2022, CarType.SUV, 3500));
        carRepo.add(new Car("KA03EF2468", "Mercedes", "E200", 2021, CarType.LUXURY, 9000));
        custRepo.add(new Customer("C100", "Alice", "alice@example.com", "DL-A1"));
        custRepo.add(new Customer("C200", "Bob", "bob@example.com", "DL-B2"));

        // search availability
        LocalDate s = LocalDate.now().plusDays(1);
        LocalDate e = s.plusDays(2);
        System.out.println("Available SUVs: " +
                search.search(CarType.SUV, 0, 10000, s, e));

        // simulate concurrent booking of SAME car and dates
        Runnable task1 = () -> reservations.create("C100", "KA02CD5678", s, e);
        Runnable task2 = () -> reservations.create("C200", "KA02CD5678", s, e);

        Thread t1 = new Thread(task1, "T-Alice");
        Thread t2 = new Thread(task2, "T-Bob");
        t1.start(); t2.start();
        t1.join(); t2.join();

        // modify a reservation (if first succeeded)
        Reservation any = resRepo.findByCar("KA02CD5678").stream()
                .filter(r -> r.getStatus() == ReservationStatus.ACTIVE).findFirst().orElse(null);
        if (any != null) {
            reservations.modify(any.getId(), "KA01AB1234",
                    s.plusDays(3), s.plusDays(5)); // change car + dates
        }

        // cancel (just to show)
        if (any != null) reservations.cancel(any.getId());
    }
}
