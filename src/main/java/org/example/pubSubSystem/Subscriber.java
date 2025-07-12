package org.example.pubSubSystem;

public interface Subscriber {
    void consume(String topic, String message);
}
