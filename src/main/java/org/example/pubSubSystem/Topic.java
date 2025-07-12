package org.example.pubSubSystem;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;

public class Topic {
    private final String name;
    private final List<Subscriber> subscribers = new CopyOnWriteArrayList<>();
    private final BlockingQueue<String> messages = new LinkedBlockingQueue<>();

    public Topic(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void publish(String message) throws InterruptedException {
        messages.put(message);
    }

    public void dispatchMessage(ExecutorService executor) {
        executor.submit(() ->{
            while (true) {
                try {
                    String message = messages.take();
                    System.out.println("[DISPATCHER] Sending to subscribers of topic: " + name + " → " + message);
                    for (Subscriber subscriber : subscribers) {
                        executor.submit(() -> subscriber.consume(name, message));
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
    }
}
