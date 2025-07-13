package org.example.parkingLot;

import java.util.*;

public class ParkingLotManager {

    private static final ParkingLotManager INSTANCE = new ParkingLotManager();
    private final List<ParkingLevel> levels = new ArrayList<>();
    private final FeeCalculationStrategy strategy = new HourlyFeeStrategy();
    private final Map<String, Ticket> tickets = new HashMap<>();

    Random random = new Random();
    int hoursAgo = random.nextInt(5) + 1;
    long randomEntryTime = System.currentTimeMillis() - hoursAgo * 60L * 60L * 1000L;

    private ParkingLotManager() {}

    public static ParkingLotManager getInstance() {
        return INSTANCE;
    }

    public void addLevel(ParkingLevel level) {
        levels.add(level);
    }

    public Ticket parkVehicle(Vehicle vehicle) {
        for (ParkingLevel level : levels) {
            SpotType spotType = mapVehicleToSpot(vehicle.getType());
            Optional<ParkingSpot> spot = level.findFreeSpot(spotType);
            if (spot.isPresent()) {
                ParkingSpot parkingSpot = spot.get();
                parkingSpot.occupy();
                Ticket ticket = new Ticket(vehicle, parkingSpot, randomEntryTime);
                tickets.put(vehicle.getPlateNumber(), ticket);
                System.out.println("Parked at level " + level.getLevelNumber() + ", spot " + parkingSpot.getId());
                return ticket;
            }
        }
        System.out.println("No Spot Available");
        return null;
    }

    public void unparkVehicle(String plateNumber) {
        Ticket ticket = tickets.remove(plateNumber);
        if (ticket == null) {
            System.out.println("Vehicle not found");
        }
        ParkingSpot spot = ticket.getSpot();
        long duration = System.currentTimeMillis() - ticket.getEntryTime();
        double fees = strategy.calculateFees(duration);
        spot.free();
        System.out.println("Unparked vehicle " + plateNumber + ". Fee: ₹" + fees);
    }

    private SpotType mapVehicleToSpot(VehicleType type) {
       return switch (type) {
            case BIKE -> SpotType.BIKE;
            case CAR -> SpotType.CAR;
            case TRUCK -> SpotType.TRUCK;
        };
    }
}
