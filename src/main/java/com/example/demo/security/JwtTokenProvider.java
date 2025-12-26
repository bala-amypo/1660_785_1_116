@Component
public class JwtTokenProvider {

    String jwtSecret;
    Long jwtExpirationMs;

    public String generateToken(Long id, String email, Set<String> roles) {
        return Jwts.builder()
                .claim("userId", id)
                .claim("email", email)
                .claim("roles", String.join(",", roles))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
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
