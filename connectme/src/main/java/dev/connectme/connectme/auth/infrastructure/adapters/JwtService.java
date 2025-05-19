package dev.connectme.connectme.auth.infrastructure.adapters;

import dev.connectme.connectme.auth.application.ports.TokenServicePort;
import dev.connectme.connectme.auth.domain.models.Token;
import dev.connectme.connectme.user.domain.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService implements TokenServicePort {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private long accessTokenExpiration;

    @Value("${application.security.jwt.refresh-token.expiration}")
    private long refreshTokenExpiration;

    @Override
    public Token generateAccessToken(User user) {
        return buildToken(user, accessTokenExpiration);
    }

    @Override
    public Token generateRefreshToken(User user) {
        return buildToken(user, refreshTokenExpiration);
    }

    @Override
    public Token buildToken(User user, long expiration) {
        Date expirationDate = new Date(System.currentTimeMillis() + expiration);
        String token = Jwts.builder()
                .id(user.getId().toString())
                .subject(user.getPhone().getNumber())
                .claim("verify", user.isVerify())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expirationDate)
                .signWith(getSignInKey())
                .compact();
        return Token.builder()
                .token(token)
                .expired(false)
                .revoked(false)
                .tokenType(Token.TokenType.BEARER)
                .build();
    }

    private SecretKey getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
