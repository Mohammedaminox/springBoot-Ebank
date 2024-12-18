package com.system.ebanky.Util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "averylongandsesfvksjdvljqmiomkey12345averylongandsecuresecretkey12345";
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

    public String generateToken(Long userId, String role) {
        try {
            return Jwts.builder()
                    .claim("userId", userId)
                    .claim("role", role)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                    .compact();
        } catch (Exception e) {
            System.err.println("Error generating JWT: " + e.getMessage());
            throw new RuntimeException("JWT generation failed", e);
        }
    }


    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            extractClaims(token); // If parsing succeeds, the token is valid
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getUserId(String token) {
        return extractClaims(token).get("userId", Long.class);
    }

    public String getRole(String token) {
        return extractClaims(token).get("role", String.class);
    }

    public String getRoles(String token) {
        Claims claims = extractClaims(token);
        return claims.get("roles").toString(); // Replace "roles" with the actual key in your JWT
    }

}
