package org.example.rateLimiter;

import java.util.concurrent.locks.ReentrantLock;

public class UserBucket {

    private final int capacity;
    private final double refillRatePerSec;
    private double currentTokens;
    private long lastRefillTimestamp;

    private final ReentrantLock lock = new ReentrantLock();

    public UserBucket(int capacity, double refillRatePerSec) {
        this.capacity = capacity;
        this.refillRatePerSec = refillRatePerSec;
        this.currentTokens = capacity;
        this.lastRefillTimestamp = System.nanoTime();
    }

    public boolean allowRequest() {
        lock.lock();
        try {
            refill();
            if (currentTokens >= 1) {
                currentTokens--;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    private void refill() {
        long now = System.nanoTime();
        double secondsPassed = (now - lastRefillTimestamp) / 1_000_000_000.0;

        double tokensToAdd = secondsPassed * refillRatePerSec;
        if (tokensToAdd > 0) {
            currentTokens = Math.min(capacity, currentTokens + tokensToAdd);
            lastRefillTimestamp = now;
        }
    }
}
