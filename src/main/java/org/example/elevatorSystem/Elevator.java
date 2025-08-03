package org.example.elevatorSystem;

import java.util.TreeSet;

public class Elevator {

    private int id;
    private Direction direction;
    private ElevatorState state;
    private int currentFloor;
    TreeSet<Integer> destinations;

    public Elevator(int id) {
        this.id = id;
        this.direction = Direction.IDLE;
        this.state = ElevatorState.IDLE;
        this.currentFloor = 0;
        this.destinations = new TreeSet<>();
    }

    public void addDestination(int floor) {
        destinations.add(floor);
        updateDirection();
    }

    private void updateDirection() {
        if (destinations.isEmpty()) {
            direction = Direction.IDLE;
            state = ElevatorState.IDLE;
        } else {
            int next = destinations.first();
            if (next > currentFloor) direction = Direction.UP;
            else if (next < currentFloor) {
                direction = Direction.DOWN;
            }
        }
    }

    public void move() {
        if (destinations.isEmpty()) {
            state = ElevatorState.IDLE;
            direction = Direction.IDLE;
            return;
        }

        state = ElevatorState.MOVING;

        if (direction == Direction.UP) {
            currentFloor++;
        } else if (direction == Direction.DOWN) {
            currentFloor--;
        }

        System.out.println("Elevator " + id + " at floor " + currentFloor);

        if (destinations.contains(currentFloor)) {
            destinations.remove(currentFloor);
            state = ElevatorState.STOPPED;
            System.out.println("Elevator " + id + " stopped at floor " + currentFloor);
        }

        updateDirection();
    }

    public int getId() {
        return id;
    }

    public Direction getDirection() {
        return direction;
    }

    public ElevatorState getState() {
        return state;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public boolean isIdle() {
        return state == ElevatorState.IDLE;
    }
}
