package dev.connectme.connectme.auth.infrastructure.repositories;

import dev.connectme.connectme.auth.domain.models.Token;
import dev.connectme.connectme.auth.infrastructure.entities.TokenEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class JpaTokenRepositoryAdapterTest {
    @Mock
    private JpaTokenRepository jpaTokenRepository;

    @InjectMocks
    private JpaTokenRepositoryAdapter jpaTokenRepositoryAdapter;

    @Test
    void shouldSaveTokenCorrectly() {
        Token tokenToSave = Token.builder()
                .tokenType(Token.TokenType.BEARER)
                .id(1L)
                .token("token")
                .revoked(false)
                .expired(false)
                .build();
        TokenEntity tokenSaved = TokenEntity.builder()
                .tokenType(Token.TokenType.BEARER)
                .id(1L)
                .token("token")
                .revoked(false)
                .expired(false)
                .build();
        when(jpaTokenRepository.save(any(TokenEntity.class))).thenReturn(tokenSaved);

        Token token = jpaTokenRepositoryAdapter.save(tokenToSave);

        assertNotNull(token);
        assertEquals(token.getTokenType(), tokenToSave.getTokenType());
        assertEquals(token.getToken(), tokenToSave.getToken());
        assertEquals(token.isExpired(), tokenToSave.isExpired());
        assertEquals(token.isRevoked(), tokenToSave.isRevoked());

    }
}
