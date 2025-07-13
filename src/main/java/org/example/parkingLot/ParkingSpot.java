package org.example.parkingLot;

public abstract class ParkingSpot {

    private final String id;
    private final SpotType type;
    private boolean isOccupied;

    public ParkingSpot(String id, SpotType type) {
        this.id = id;
        this.type = type;
        this.isOccupied = false;
    }

    public String getId() {
        return id;
    }

    public SpotType getType() {
        return type;
    }

    public void occupy() {
        isOccupied = true;
    }

    public void free() {
        isOccupied = false;
    }

    public boolean isOccupied() {
        return isOccupied;
    }
}
