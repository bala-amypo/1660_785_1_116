package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Set;

@Component
public class JwtTokenProvider {

    // 🔥 MUST HAVE DEFAULT VALUES
    private String jwtSecret = "my-super-secret-key-my-super-secret-key";
    private Long jwtExpirationMs = 3600000L; // 1 hour

    public String generateToken(Long userId, String email, Set<String> roles) {

        return Jwts.builder()
                .claim("userId", userId)
                .claim("email", email)
                .claim("roles", String.join(",", roles))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes()))
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
