package org.example.jwtAuth;

import io.jsonwebtoken.*;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class JwtUtil {

    public static String createAccessToken(String subject, Map<String, Object> claims) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setId(UUID.randomUUID().toString())        // jti
                .setSubject(subject)
                .addClaims(claims)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(JwtConfig.ACCESS_TOKEN_TTL)))
                .signWith(JwtConfig.SIGNING_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public static Jws<Claims> parseAndValidate(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(JwtConfig.SIGNING_KEY)
                .build()
                .parseClaimsJws(token);
    }
}
