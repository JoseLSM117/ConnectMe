package dev.connectme.connectme.auth.infrastructure.entities;

import dev.connectme.connectme.auth.domain.models.Token;
import dev.connectme.connectme.auth.infrastructure.repositories.JpaTokenRepository;
import dev.connectme.connectme.user.domain.models.User;
import dev.connectme.connectme.user.infrastructure.entities.UserEntity;
import dev.connectme.connectme.user.infrastructure.entities.UserEntityTest;
import dev.connectme.connectme.user.infrastructure.repositories.JpaUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
public class TokenEntityTest {
    @Autowired
    private JpaTokenRepository jpaTokenRepository;

    @Autowired
    private JpaUserRepository jpaUserRepository;

    private UserEntity user;

    @BeforeEach
    void setUp(){
        user = UserEntity.builder()
            .password("5569382066")
            .country("Mexico")
            .email("test@test.com")
            .gender(User.Gender.MALE)
            .isVerify(false)
            .createAt(new Date())
            .updateAt(new Date())
            .profilePicture("nose.png")
            .lastName("sanchez")
            .firstName("jose")
            .build();
        jpaUserRepository.save(user);

    }

    @Test
    void shouldInstanceCorrectly() {

        TokenEntity tokenEntity = TokenEntity.builder()
                .token("token")
                .expired(false)
                .revoked(false)
                .tokenType(Token.TokenType.BEARER)
                .user(user)
                .build();
        TokenEntity tokenSaved = jpaTokenRepository.save(tokenEntity);

        assertNotNull(tokenSaved);
        assertEquals(tokenSaved.getToken(), tokenEntity.getToken());
        assertNotNull(tokenSaved.getId());
        assertEquals(tokenSaved.getTokenType(), tokenEntity.getTokenType());
    }

    @Test
    void shouldThrowExceptionIfTokenIsNull() {
        assertThrows(Exception.class, () -> {
            TokenEntity tokenEntity = TokenEntity.builder()
                    .expired(false)
                    .revoked(false)
                    .tokenType(Token.TokenType.BEARER)
                    .user(user)
                    .build();
            TokenEntity tokenSaved = jpaTokenRepository.save(tokenEntity);
        });
    }

    @Test
    void shouldAssignDefaultValueIfExpirationIsNull() {
        TokenEntity tokenEntity = TokenEntity.builder()
                .token("token")
                .revoked(false)
                .tokenType(Token.TokenType.BEARER)
                .user(user)
                .build();
        assertFalse(tokenEntity.isExpired());
    }

    @Test
    void shouldAssignDefaultValueIfRevokedIsNull() {
        TokenEntity tokenEntity = TokenEntity.builder()
                .token("token")
                .expired(false)
                .tokenType(Token.TokenType.BEARER)
                .user(user)
                .build();
        assertFalse(tokenEntity.isRevoked());
    }

    @Test
    void shouldThrowExceptionIfTokenTypeIsNull() {
        assertThrows(Exception.class, () -> {
            TokenEntity tokenEntity = TokenEntity.builder()
                    .token("token")
                    .expired(false)
                    .revoked(false)
                    .user(user)
                    .build();
            jpaTokenRepository.save(tokenEntity);
        });
    }
    @Test
    void shouldThrowExceptionIfUserIsNull() {
        assertThrows(Exception.class, () -> {
            TokenEntity tokenEntity = TokenEntity.builder()
                    .token("token")
                    .expired(false)
                    .revoked(false)
                    .tokenType(Token.TokenType.BEARER)
                    .build();
            jpaTokenRepository.save(tokenEntity);
        });
    }
}
