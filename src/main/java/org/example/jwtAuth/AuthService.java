package org.example.jwtAuth;

import java.time.Instant;
import java.util.*;

public class AuthService {
    // demo in-memory user store: username -> password (in prod: hashed + DB)
    private final Map<String, String> users = new HashMap<>();
    private final RefreshTokenStore refreshStore = new RefreshTokenStore();

    public AuthService() {
        // demo user
        users.put("alice", "password123");
    }

    // 1) Login: issue access + refresh (refresh is a strong random opaque string)
    public AuthResponse login(String username, String password, String deviceId) {
        // validate credentials (demo)
        if (!users.containsKey(username) || !users.get(username).equals(password)) {
            throw new RuntimeException("Bad credentials");
        }
        Map<String,Object> claims = new HashMap<>();
        claims.put("role","USER");

        String accessToken = JwtUtil.createAccessToken(username, claims);

        // create refresh token (opaque random string)
        String refreshToken = generateSecureRandomToken();
        Instant expiry = Instant.now().plus(JwtConfig.REFRESH_TOKEN_TTL);

        // persist hashed token
        refreshStore.put(refreshToken, username, expiry, deviceId);

        return new AuthResponse(accessToken, refreshToken, expiry);
    }

    // 2) Refresh: rotation + reuse detection
    public AuthResponse refresh(String refreshToken, String deviceId) {
        RefreshTokenStore.RefreshRecord rec = refreshStore.get(refreshToken);
        if (rec == null) {
            // token not found => reuse or invalid; treat as compromise
            // You can choose to revoke all user tokens for safety if you can map username (if token contains jti you can detect)
            throw new RuntimeException("Invalid or expired refresh token");
        }

        // optional: verify deviceId matches
        if (rec.deviceId != null && !rec.deviceId.equals(deviceId)) {
            // suspicious: token used from different device
            refreshStore.revokeAllForUser(rec.userId);
            throw new RuntimeException("Refresh token used from different device; all tokens revoked");
        }

        // rotation: remove old token, create new
        refreshStore.removeByToken(refreshToken);

        // issue new tokens
        Map<String,Object> claims = new HashMap<>(); claims.put("role","USER");
        String newAccess = JwtUtil.createAccessToken(rec.userId, claims);
        String newRefresh = generateSecureRandomToken();
        Instant newExpiry = Instant.now().plus(JwtConfig.REFRESH_TOKEN_TTL);
        refreshStore.put(newRefresh, rec.userId, newExpiry, deviceId);

        return new AuthResponse(newAccess, newRefresh, newExpiry);
    }

    // Logout or revoke single token
    public void logout(String refreshToken) {
        refreshStore.removeByToken(refreshToken);
    }

    // revoke all user refresh tokens (admin or suspicious)
    public void revokeAll(String username) {
        refreshStore.revokeAllForUser(username);
    }

    private static String generateSecureRandomToken() {
        byte[] b = new byte[64];
        new java.security.SecureRandom().nextBytes(b);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(b);
    }

    public static final class AuthResponse {
        public final String accessToken;
        public final String refreshToken;
        public final Instant refreshExpiry;
        AuthResponse(String accessToken, String refreshToken, Instant refreshExpiry) {
            this.accessToken = accessToken; this.refreshToken = refreshToken; this.refreshExpiry = refreshExpiry;
        }
    }
}

