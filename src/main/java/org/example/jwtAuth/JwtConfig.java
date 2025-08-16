package org.example.jwtAuth;

import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.time.Duration;

public class JwtConfig {

    // In prod, store secrets in a KMS / env var.
    public static final byte[] SECRET = "very-very-long-secret-key-should-be-from-kms-or-env-VERYLONG".getBytes();
    public static final Key SIGNING_KEY = Keys.hmacShaKeyFor(SECRET);

    public static final Duration ACCESS_TOKEN_TTL = Duration.ofMinutes(15);
    public static final Duration REFRESH_TOKEN_TTL = Duration.ofDays(14);
}
