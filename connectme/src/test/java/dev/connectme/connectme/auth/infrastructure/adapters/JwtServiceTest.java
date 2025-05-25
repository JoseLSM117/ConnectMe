package dev.connectme.connectme.auth.infrastructure.adapters;

import dev.connectme.connectme.auth.domain.models.Token;
import dev.connectme.connectme.phone.domain.models.Phone;
import dev.connectme.connectme.user.domain.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.crypto.SecretKey;
import java.util.Base64;
import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;
    private User user;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();

        String secret = Base64.getEncoder().encodeToString("super-secret-key-which-should-be-long".getBytes());
        ReflectionTestUtils.setField(jwtService, "secretKey", secret);
        ReflectionTestUtils.setField(jwtService, "accessTokenExpiration", 86400000L);
        ReflectionTestUtils.setField(jwtService, "refreshTokenExpiration", 604800000L);

        user = User.builder()
                .id(1L)
                .isVerify(true)
                .phone(Phone.builder().number("5569382066").build())
                .build();
    }

    @Test
    void shouldGenerateAccessTokenWithCorrectClaims() {
        Phone phone = Phone.builder()
                .id(1L)
                .number("5569382066")
                .user(user)
                .build();
        Token token = jwtService.generateAccessToken(phone);

        assertNotNull(token);
        assertNotNull(token.getToken());
        assertFalse(token.isExpired());
        assertFalse(token.isRevoked());
        assertEquals(Token.TokenType.BEARER, token.getTokenType());
        assertEquals(Token.TokenPurpose.ACCESS, token.getTokenPurpose());

        Claims claims = parseClaims(token.getToken(), getKeyFromService());
        assertEquals("5569382066", claims.getSubject());
        assertEquals("1", claims.getId());
        assertEquals(true, claims.get("verify"));
    }

    @Test
    void shouldGenerateRefreshTokenWithDifferentExpiration() {
        Phone phone = Phone.builder()
                .id(1L)
                .number("5569382066")
                .user(user)
                .build();
        Token accessToken = jwtService.generateAccessToken(phone);
        Token refreshToken = jwtService.generateRefreshToken(phone);

        Claims accessClaims = parseClaims(accessToken.getToken(), getKeyFromService());
        Claims refreshClaims = parseClaims(refreshToken.getToken(), getKeyFromService());

        long accessExp = accessClaims.getExpiration().getTime();
        long refreshExp = refreshClaims.getExpiration().getTime();

        assertTrue(refreshExp > accessExp);
    }

    private SecretKey getKeyFromService() {
        String secret = (String) ReflectionTestUtils.getField(jwtService, "secretKey");
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Claims parseClaims(String token, SecretKey key) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
