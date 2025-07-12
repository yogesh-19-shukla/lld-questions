package org.example.pubSubSystem;

public class PrintSubscriber implements Subscriber {

    private final String name;

    public PrintSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void consume(String topic, String message) {
        try {
            System.out.println("[" + name + "] received on " + topic + " with message: " + message);
        } catch (Exception e) {
            System.err.println("[" + name + "] Error in consume(): " + e.getMessage());
        }
    }
}
