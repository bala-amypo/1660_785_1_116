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


// package com.example.demo.security;

// import io.jsonwebtoken.Claims;
// import io.jsonwebtoken.Jwts;
// import io.jsonwebtoken.SignatureAlgorithm;
// import org.springframework.stereotype.Component;

// import java.util.Date;
// import java.util.Set;
// import java.util.stream.Collectors;

// @Component
// public class JwtTokenProvider {
    
//     private String jwtSecret = "defaultSecret";
//     private Long jwtExpirationMs = 86400000L; // 24 hours

//     public String generateToken(Long userId, String email, Set<String> roles) {
//         Date expiryDate = new Date(System.currentTimeMillis() + jwtExpirationMs);
        
//         return Jwts.builder()
//                 .setSubject(email)
//                 .claim("userId", userId)
//                 .claim("email", email)
//                 .claim("roles", String.join(",", roles))
//                 .setIssuedAt(new Date())
//                 .setExpiration(expiryDate)
//                 .signWith(SignatureAlgorithm.HS512, jwtSecret)
//                 .compact();
//     }

//     public boolean validateToken(String token) {
//         try {
//             Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//             return true;
//         } catch (Exception e) {
//             return false;
//         }
//     }

//     public Claims getClaims(String token) {
//         return Jwts.parser()
//                 .setSigningKey(jwtSecret)
//                 .parseClaimsJws(token)
//                 .getBody();
//     }
// }


package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class JwtTokenProvider {

    private static final String SECRET_KEY =
            "mysecretkeymysecretkeymysecretkeymysecretkey";

    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000; // 1 day

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // ✅ GENERATE TOKEN (THIS FIXES YOUR ERROR)
    public String generateToken(Long userId, String email, Set<String> roles) {

        return Jwts.builder()
                .setSubject(email)                 // username / email
                .claim("userId", userId)           // custom claim
                .claim("roles", roles)             // roles
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // ✅ VALIDATE TOKEN
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // ✅ EXTRACT USERNAME
    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    // ✅ EXTRACT ROLES
    public Set<String> getRoles(String token) {
        List<String> roles = getClaims(token).get("roles", List.class);
        return Set.copyOf(roles);
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
