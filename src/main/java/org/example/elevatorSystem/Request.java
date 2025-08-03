package org.example.elevatorSystem;

public abstract class Request {
    int floor;

    public Request(int floor) {
        this.floor = floor;
    }

    public int getFloor() {
        return floor;
    }
}
