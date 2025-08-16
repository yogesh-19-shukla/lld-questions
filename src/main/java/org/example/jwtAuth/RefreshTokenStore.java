package org.example.jwtAuth;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;

// Demo in-memory store. Replace with Redis for prod.
public class RefreshTokenStore {
    // map<tokenHash, RefreshRecord>
    private final ConcurrentHashMap<String, RefreshRecord> store = new ConcurrentHashMap<>();

    // record by user to assist revoking all tokens for a user
    private final ConcurrentHashMap<String, Set<String>> byUser = new ConcurrentHashMap<>();

    static class RefreshRecord {
        final String userId;
        final String tokenHash;
        final Instant expiresAt;
        final String deviceId; // optional: allow tokens per device
        RefreshRecord(String userId, String tokenHash, Instant expiresAt, String deviceId) {
            this.userId = userId; this.tokenHash = tokenHash; this.expiresAt = expiresAt; this.deviceId = deviceId;
        }
    }

    public static String hashToken(String token) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] h = md.digest(token.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(h);
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    public void put(String token, String userId, Instant expiry, String deviceId) {
        String hash = hashToken(token);
        RefreshRecord rec = new RefreshRecord(userId, hash, expiry, deviceId);
        store.put(hash, rec);
        byUser.computeIfAbsent(userId, k -> ConcurrentHashMap.newKeySet()).add(hash);
    }

    public RefreshRecord get(String token) {
        String hash = hashToken(token);
        RefreshRecord r = store.get(hash);
        if (r == null) return null;
        if (r.expiresAt.isBefore(Instant.now())) {
            removeByHash(hash);
            return null;
        }
        return r;
    }

    public void removeByHash(String hash) {
        RefreshRecord r = store.remove(hash);
        if (r != null) {
            Set<String> s = byUser.get(r.userId);
            if (s != null) s.remove(hash);
        }
    }

    public void removeByToken(String token) {
        removeByHash(hashToken(token));
    }

    public void revokeAllForUser(String userId) {
        Set<String> set = byUser.getOrDefault(userId, Collections.emptySet());
        for (String hash : new ArrayList<>(set)) {
            store.remove(hash);
        }
        byUser.remove(userId);
    }
}
