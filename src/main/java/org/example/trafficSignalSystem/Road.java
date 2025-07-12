package org.example.trafficSignalSystem;

public class Road {
    private String name;
    private String id;
    private TrafficLight trafficLight;

    public Road(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public TrafficLight getTrafficLight() {
        return trafficLight;
    }

    public void setTrafficLight(TrafficLight trafficLight) {
        this.trafficLight = trafficLight;
    }
}
