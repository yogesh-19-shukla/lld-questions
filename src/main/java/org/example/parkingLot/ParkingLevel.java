package org.example.parkingLot;

import java.util.List;
import java.util.Optional;

public class ParkingLevel {

    private final int levelNumber;
    private final List<ParkingSpot> spots;

    public ParkingLevel(int levelNumber, List<ParkingSpot> spots) {
        this.levelNumber = levelNumber;
        this.spots = spots;
    }

    public Optional<ParkingSpot> findFreeSpot(SpotType type) {
        return spots.stream().filter(s -> s.getType() == type && !s.isOccupied()).findFirst();
    }

    public int getLevelNumber() {
        return levelNumber;
    }
}
