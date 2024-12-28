package saraya.services.user;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JWTService {
    private static final String SECRET = "eEq/PAQH3Ubv74tgJvjIzX6D8WRJ+50IgALpaM7DLrCBvFQWJ0vdjCU8ZvMEvC3e";
    private static final Key KEY = Keys.hmacShaKeyFor(SECRET.getBytes());
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;

    public String generateToken(Authentication authentication) {
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("roles", authentication.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isValidToken(String token, String username) {
        Claims body = getClaimsFromToken(token);
        String tokenUsername = body.getSubject();
        boolean notExpired = body.getExpiration().after(new Date());
        return username.equals(tokenUsername) && notExpired;
    }

    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
