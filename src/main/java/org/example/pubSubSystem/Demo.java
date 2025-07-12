package org.example.pubSubSystem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        MessageBroker broker = new MessageBroker();

        broker.createTopic("cricket");
        broker.createTopic("tech");
        broker.createTopic("movies");

        Subscriber user1 = new PrintSubscriber("User1");
        Subscriber user2 = new PrintSubscriber("User2");
        Subscriber user3 = new PrintSubscriber("User3");

        broker.subscribe("cricket", user1);
        broker.subscribe("tech", user2);
        broker.subscribe("movies", user3);
        broker.subscribe("cricket", user3);

        // List of topics
        String[] topics = {"cricket", "tech", "movies"};

        ExecutorService publisherExecutor = Executors.newFixedThreadPool(3);

        // Create and run 3 publishers concurrently
        publisherExecutor.submit(new Publisher("Publisher-A", broker, topics));
        publisherExecutor.submit(new Publisher("Publisher-B", broker, topics));
        publisherExecutor.submit(new Publisher("Publisher-C", broker, topics));

        publisherExecutor.shutdown();
        publisherExecutor.awaitTermination(10, TimeUnit.SECONDS);

        Thread.sleep(3000); // wait for all messages to be delivered
        broker.shutdown();
    }
}
