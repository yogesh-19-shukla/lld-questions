package org.example.trafficSignalSystem;

import java.util.HashMap;
import java.util.Map;

public class TrafficController {

    private static TrafficController instance;
    private final Map<String, Road> roads;

    private TrafficController() {
        roads = new HashMap<>();
    }

    public static synchronized TrafficController getInstance() {
        if(instance == null)  instance = new TrafficController();
        return instance;
    }

    public void addRoad(Road road) {
        roads.put(road.getId(), road);
    }

    public void removeRoad(String roadId) {
        roads.remove(roadId);
    }

    public void startController() {
        for (Road road: roads.values()){
            TrafficLight trafficLight = road.getTrafficLight();

            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(trafficLight.getRedDuration());
                        trafficLight.changeSignal(Signal.GREEN);
                        System.out.println(road.getId() + " signal is now " + road.getTrafficLight().getCurrentSignal().name() + " being handled by " + Thread.currentThread().getName());
                        Thread.sleep(trafficLight.getGreenDuration());
                        trafficLight.changeSignal(Signal.YELLOW);
                        System.out.println(road.getId() + " signal is now " + road.getTrafficLight().getCurrentSignal().name() + " being handled by " + Thread.currentThread().getName());
                        Thread.sleep(trafficLight.getYellowDuration());
                        trafficLight.changeSignal(Signal.RED);
                        System.out.println(road.getId() + " signal is now " + road.getTrafficLight().getCurrentSignal().name() + " being handled by " + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    public void handleEmergency(String roadId) {
        Road road = roads.get(roadId);
        if(road != null) {
            TrafficLight trafficLight = road.getTrafficLight();
            trafficLight.changeSignal(Signal.GREEN);
            // perform emergency handling logic
        }
    }
}
