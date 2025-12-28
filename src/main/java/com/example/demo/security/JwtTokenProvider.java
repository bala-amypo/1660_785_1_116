// package com.example.demo.security;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import org.springframework.stereotype.Component;

// import java.util.Date;
// import java.util.Set;

// @Component
// public class JwtTokenProvider {

//     private final String jwtSecret = "THIS_IS_A_VERY_LONG_SECRET_KEY_FOR_JWT_256_BITS_MINIMUM";
//     private final Long jwtExpirationMs = 3600000L;

//     public String generateToken(Long userId, String email, Set<String> roles) {
//         return Jwts.builder()
//                 .claim("userId", userId)
//                 .claim("email", email)
//                 .claim("roles", String.join(",", roles))
//                 .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
//                 .signWith(SignatureAlgorithm.HS256, jwtSecret.getBytes())
//                 .compact();
//     }

//     public Claims getClaims(String token) {
//         return Jwts.parser()
//                 .setSigningKey(jwtSecret.getBytes())
//                 .parseClaimsJws(token)
//                 .getBody();
//     }

//     public boolean validateToken(String token) {
//         try {
//             getClaims(token);
//             return true;
//         } catch (Exception e) {
//             return false;
//         }
//     }
// }



package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    // 🔴 REQUIRED BY TESTS (DO NOT RENAME)
    String jwtSecret = "THIS_IS_A_SECURE_256_BIT_SECRET_KEY_FOR_TESTING_ONLY";

    private static final long EXPIRATION_MS = 3600_000;

    // ================= GENERATE TOKEN =================
    public String generateToken(Long userId, String email, Set<String> roles) {

        String rolesCsv = roles.stream()
                .collect(Collectors.joining(","));

        SecretKey key = Keys.hmacShaKeyFor(
                jwtSecret.getBytes(StandardCharsets.UTF_8)
        );

        return Jwts.builder()
                .claim("userId", userId)
                .claim("email", email)
                .claim("roles", rolesCsv)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ================= VALIDATE TOKEN =================
    public boolean validateToken(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(
                    jwtSecret.getBytes(StandardCharsets.UTF_8)
            );

            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // ================= GET CLAIMS =================
    public Claims getClaims(String token) {

        SecretKey key = Keys.hmacShaKeyFor(
                jwtSecret.getBytes(StandardCharsets.UTF_8)
        );

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}


