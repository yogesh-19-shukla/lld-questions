package org.example.rateLimiter;

import java.util.concurrent.ConcurrentHashMap;

public class TokenBucket implements RateLimiter {

    private final ConcurrentHashMap<String, UserBucket> userBuckets;
    private final int capacity;
    private final double refillRate;

    public TokenBucket(int capacity, double refillRate) {
        this.userBuckets = new ConcurrentHashMap<>();
        this.capacity = capacity;
        this.refillRate = refillRate;
    }

    @Override
    public boolean allowRequest(String userId) {
        userBuckets.putIfAbsent(userId, new UserBucket(capacity, refillRate));
        return userBuckets.get(userId).allowRequest();
    }
}
