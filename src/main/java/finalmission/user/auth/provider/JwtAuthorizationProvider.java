package finalmission.user.auth.provider;

import finalmission.exception.UnauthorizedException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtAuthorizationProvider {
    private final String secretKey;
    private final Long validityInMilliseconds;

    public JwtAuthorizationProvider(
            @Value("${jwt.secret-key}") String secretKey,
            @Value("${jwt.validity-in-milliseconds}") Long validityInMilliseconds
    ) {
        this.secretKey = secretKey;
        this.validityInMilliseconds = validityInMilliseconds;
    }

    public String createToken(AuthorizationPayload payload) {
        Claims claims = Jwts.claims();
        claims.put("email", payload.email());

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public AuthorizationPayload getPayload(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();

        String email = claims.get("email", String.class);

        return new AuthorizationPayload(email);
    }

    public void validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            claims.getBody().getExpiration();
        } catch (JwtException | IllegalArgumentException exception) {
            throw new UnauthorizedException("인증 정보가 올바르지 않습니다.");
        }
    }
}
