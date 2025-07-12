package org.example.rateLimiter;

public interface RateLimiter {

    boolean allowRequest(String userId);
}
