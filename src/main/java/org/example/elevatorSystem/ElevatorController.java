package org.example.elevatorSystem;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {

    private List<Elevator> elevators;

    public ElevatorController(int numElevators) {
        elevators = new ArrayList<>();
        for (int i=0; i<numElevators; i++) {
            elevators.add(new Elevator(i));
        }
    }

    public void handleExternalRequest(ExternalRequest request) {
        Elevator bestElevator = findBestElevator(request);
        if (bestElevator != null) {
            bestElevator.addDestination(request.getFloor());
        }
    }

    public void handleInternalRequest(int elevatorId, InternalRequest request) {
        Elevator elevator = elevators.get(elevatorId);
        elevator.addDestination(request.getFloor());
    }

    private Elevator findBestElevator(ExternalRequest request) {
        Elevator bestElevator = null;
        int minDistance = Integer.MAX_VALUE;
        for (Elevator e: elevators) {
            if (e.isIdle()) {
                int distance = Math.abs(e.getCurrentFloor() - request.getFloor());
                if (distance < minDistance) {
                    minDistance = distance;
                    bestElevator = e;
                } else if (e.getDirection() == request.getDirection()) {
                    if ((request.getDirection() == Direction.UP && e.getCurrentFloor() <= request.getFloor())
                    || (request.getDirection() == Direction.DOWN && e.getCurrentFloor() > request.getFloor())) {
                        distance = Math.abs(e.getCurrentFloor() - request.getFloor());
                        if (distance < minDistance) {
                            minDistance = distance;
                            bestElevator = e;
                        }
                    }

                }
            }
        }
        // fallback to first
        if (bestElevator == null)  bestElevator = elevators.get(0);
        return bestElevator;
    }

    public void stepSimulation() {
        for (Elevator e : elevators) {
            e.move();
        }
    }

}
