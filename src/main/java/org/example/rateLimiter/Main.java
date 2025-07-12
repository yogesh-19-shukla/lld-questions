package org.example.rateLimiter;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        RateLimiter rateLimiter = new TokenBucket(5, 1);
        List<String> users = Arrays.asList("user1", "user2", "user3");

        for (String user : users) {
            System.out.println("Testing for " + user);
            for (int i = 1; i <= 10; i++) {
                boolean allowed = rateLimiter.allowRequest(user);
                System.out.println("Request " + i + " for " + user + ": " + (allowed ? "✅ Allowed" : "❌ Blocked"));
                Thread.sleep(100); // simulate time between requests
            }
            System.out.println();
        }

        RateLimiter logLimiter = new SlidingWindowLog(5, 10000); // 5 requests per 10 seconds
        String user = "user123";

        for (int i = 0; i < 10; i++) {
            boolean allowed = logLimiter.allowRequest(user);
            System.out.println("Request " + (i + 1) + ": " + (allowed ? "✅ Allowed" : "❌ Blocked"));
            Thread.sleep(1000); // 1 sec between requests
        }
    }
}
