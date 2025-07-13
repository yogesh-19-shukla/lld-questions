package org.example.parkingLot;

import java.util.Date;
import java.util.List;

public class Demo {

    public static void main(String[] args) throws InterruptedException {
        ParkingLotManager manager = ParkingLotManager.getInstance();

        List<ParkingSpot> level1Spots = List.of(
                new CarSpot("C1"), new BikeSpot("B1"), new TruckSpot("T1")
        );
        manager.addLevel(new ParkingLevel(1, level1Spots));

        Vehicle car = new Vehicle("KA01AB1234", VehicleType.CAR);
        Vehicle bike = new Vehicle("KA01XY9999", VehicleType.BIKE);

        Ticket t1 = manager.parkVehicle(car);
        if (t1 != null) {
            System.out.println("Ticket issued: " + t1.getVehicle().getPlateNumber() + " at " + new Date(t1.getEntryTime()));
        }
        Ticket t2 = manager.parkVehicle(bike);
        if (t2 != null) {
            System.out.println("Ticket issued: " + t2.getVehicle().getPlateNumber() + " at " + new Date(t2.getEntryTime()));
        }

        Thread.sleep(2000); // Simulate time

        manager.unparkVehicle(car.getPlateNumber());
        manager.unparkVehicle(bike.getPlateNumber());
    }
}
