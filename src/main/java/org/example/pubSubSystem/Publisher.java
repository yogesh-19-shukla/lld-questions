package org.example.pubSubSystem;

import java.util.concurrent.ThreadLocalRandom;

public class Publisher implements Runnable {
    private final String name;
    private final MessageBroker broker;
    private final String[] topics;

    public Publisher(String name, MessageBroker broker, String[] topics) {
        this.name = name;
        this.broker = broker;
        this.topics = topics;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                String topic = topics[ThreadLocalRandom.current().nextInt(topics.length)];
                String message = "Msg-" + i + " from " + name;
                broker.publish(topic, message);
                System.out.println("[PUBLISHER: " + name + "] Published to " + topic + ": " + message);
                Thread.sleep(ThreadLocalRandom.current().nextInt(300, 1000)); // simulate delay
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
