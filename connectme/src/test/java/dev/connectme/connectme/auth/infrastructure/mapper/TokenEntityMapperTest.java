package dev.connectme.connectme.auth.infrastructure.mapper;

import dev.connectme.connectme.auth.domain.models.Token;
import dev.connectme.connectme.auth.infrastructure.entities.TokenEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class TokenEntityMapperTest {

    @Test
    void shouldConvertToEntity() {
        Token token = Token.builder()
                .token("token")
                .tokenType(Token.TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .id(1L)
                .tokenPurpose(Token.TokenPurpose.ACCESS)
                .build();
        TokenEntity tokenEntity = TokenEntity.fromDomainModel(token);

        assertNotNull(tokenEntity);
        assertEquals(tokenEntity.getId(), token.getId());
        assertEquals(tokenEntity.getTokenType(), token.getTokenType());
        assertEquals(tokenEntity.getToken(), token.getToken());
        assertEquals(tokenEntity.isRevoked(), token.isRevoked());
        assertEquals(tokenEntity.isExpired(), token.isExpired());
        assertEquals(tokenEntity.getTokenPurpose(), token.getTokenPurpose());
    }

    @Test
    void shouldConvertToDomain(){
        TokenEntity tokenEntity = TokenEntity.builder()
                .token("token")
                .tokenType(Token.TokenType.BEARER)
                .revoked(false)
                .expired(false)
                .tokenPurpose(Token.TokenPurpose.ACCESS)
                .id(1L)
                .build();
        Token token = tokenEntity.toDomainModel();

        assertNotNull(tokenEntity);
        assertEquals(tokenEntity.getId(), token.getId());
        assertEquals(tokenEntity.getTokenType(), token.getTokenType());
        assertEquals(tokenEntity.getToken(), token.getToken());
        assertEquals(tokenEntity.isRevoked(), token.isRevoked());
        assertEquals(tokenEntity.isExpired(), token.isExpired());
        assertEquals(tokenEntity.getTokenPurpose(), token.getTokenPurpose());
    }
}
