package dev.connectme.connectme.auth.infrastructure.adapters;

import dev.connectme.connectme.auth.application.ports.TokenServicePort;
import dev.connectme.connectme.auth.domain.models.Token;
import dev.connectme.connectme.phone.domain.models.Phone;
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
    public Token generateAccessToken(Phone phone) {
        return buildToken(phone, accessTokenExpiration, Token.TokenPurpose.ACCESS);
    }

    @Override
    public Token generateRefreshToken(Phone phone) {
        return buildToken(phone, refreshTokenExpiration, Token.TokenPurpose.REFRESH);
    }

    @Override
    public Token buildToken(Phone phone, long expiration, Token.TokenPurpose purpose) {
        Date expirationDate = new Date(System.currentTimeMillis() + expiration);
        String token = Jwts.builder()
                .id(phone.getUser().getId().toString())
                .subject(phone.getNumber())
                .claim("verify", phone.getUser().isVerify())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(expirationDate)
                .signWith(getSignInKey())
                .compact();
        return Token.builder()
                .token(token)
                .expired(false)
                .revoked(false)
                .tokenType(Token.TokenType.BEARER)
                .tokenPurpose(purpose)
                .build();
    }

    private SecretKey getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
