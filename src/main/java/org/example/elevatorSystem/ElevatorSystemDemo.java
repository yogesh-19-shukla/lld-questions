package org.example.elevatorSystem;

public class ElevatorSystemDemo {

    public static void main(String[] args) {
        ElevatorController controller = new ElevatorController(2);

        // External request from floor 3 going UP
        controller.handleExternalRequest(new ExternalRequest(3, Direction.UP));

        // External request from floor 5 going DOWN
        controller.handleExternalRequest(new ExternalRequest(5, Direction.DOWN));

        // Internal request inside elevator 0 to go to floor 7
        controller.handleInternalRequest(0, new InternalRequest(7));

        // Simulate elevator movement
        for (int i = 0; i < 10; i++) {
            controller.stepSimulation();
            try { Thread.sleep(500); } catch (InterruptedException e) {}
        }
    }
}
