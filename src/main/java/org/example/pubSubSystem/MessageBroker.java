package org.example.pubSubSystem;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageBroker {
    private final Map<String, Topic> topics = new ConcurrentHashMap<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(10);

    public void createTopic(String name) {
        Topic topic = new Topic(name);
        topic.dispatchMessage(executor);
        topics.put(name, topic);
    }

    public void subscribe(String topicName, Subscriber subscriber) {
        Topic topic = topics.get(topicName);
        if (topic != null) {
            topic.addSubscriber(subscriber);
        }
    }

    public void publish(String topicName, String message) throws InterruptedException {
        Topic topic = topics.get(topicName);
        if (topic != null) {
            topic.publish(message);
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
}
