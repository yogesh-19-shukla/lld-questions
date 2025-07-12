package org.example.rateLimiter;

import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SlidingWindowLog implements RateLimiter {
    private final int maxRequests;
    private final long windowSizeMillis;

    private final ConcurrentHashMap<String, Deque<Long>> userRequestLogs = new ConcurrentHashMap<>();

    public SlidingWindowLog(int maxRequests, long windowSizeMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeMillis = windowSizeMillis;
    }

    @Override
    public boolean allowRequest(String userId) {
        long now = System.currentTimeMillis();
        userRequestLogs.putIfAbsent(userId, new ConcurrentLinkedDeque<>());

        Deque<Long> timestamps = userRequestLogs.get(userId);

        synchronized (timestamps) {
            // Clean old timestamps outside the window
            while (!timestamps.isEmpty() && (now - timestamps.peekFirst()) > windowSizeMillis) {
                timestamps.pollFirst();
            }

            if (timestamps.size() < maxRequests) {
                timestamps.addLast(now);
                return true;
            } else {
                return false;
            }
        }
    }
}









