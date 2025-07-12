package org.example.trafficSignalSystem;

public class TrafficLight {
    private String id;
    private Signal currentSignal;
    private int redDuration;
    private int greenDuration;
    private int yellowDuration;

    public TrafficLight(String id, int redDuration, int greenDuration, int yellowDuration) {
        this.id = id;
        this.currentSignal = Signal.RED;
        this.redDuration = redDuration;
        this.greenDuration = greenDuration;
        this.yellowDuration = yellowDuration;
    }

    public void changeSignal(Signal signal) {
        currentSignal = signal;
        notifyObservers();
    }

    private void notifyObservers() {
        // notify logic here
    }

    public int getRedDuration() {
        return redDuration;
    }

    public void setRedDuration(int redDuration) {
        this.redDuration = redDuration;
    }

    public int getGreenDuration() {
        return greenDuration;
    }

    public void setGreenDuration(int greenDuration) {
        this.greenDuration = greenDuration;
    }

    public int getYellowDuration() {
        return yellowDuration;
    }

    public void setYellowDuration(int yellowDuration) {
        this.yellowDuration = yellowDuration;
    }

    public Signal getCurrentSignal() {
        return currentSignal;
    }
}
