package org.example.elevatorSystem;

public class ExternalRequest extends Request {
    Direction direction;

    public ExternalRequest(int floor, Direction direction) {
        super(floor);
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}
