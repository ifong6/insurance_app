package com.project.dev.utility;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static Key key = null;
    private static long expirationTime = 0;

    // Constructor with configurable values
    public JwtUtil(
            @Value("${jwt.secretKey}") String secret,
            @Value("${jwt.expirationTime}") long expirationTime) {

        key = Keys.hmacShaKeyFor(secret.getBytes());
        JwtUtil.expirationTime = expirationTime;
    }

    // Remove 'static' from methods
    public static String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }

    public String getSubject(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            getSubject(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}