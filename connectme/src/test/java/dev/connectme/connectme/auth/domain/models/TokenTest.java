package dev.connectme.connectme.auth.domain.models;

import dev.connectme.connectme.user.domain.models.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TokenTest {
    private User user;
    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .password("123456")
                .country("Mexico")
                .email("test@test.com")
                .gender(User.Gender.MALE)
                .isVerify(false)
                .createAt(new Date())
                .updateAt(new Date())
                .profilePicture("profile.png")
                .lastName("Lopez")
                .firstName("Carlos")
                .build();
    }

    @Test
    void shouldInstanceCorrectly() {
        Token token = Token.builder()
                .id(1L)
                .token("abc123")
                .expired(false)
                .revoked(false)
                .tokenType(Token.TokenType.BEARER)
                .user(user)
                .build();

        assertNotNull(token);
        assertEquals(1L, token.getId());
        assertEquals("abc123", token.getToken());
        assertFalse(token.isExpired());
        assertFalse(token.isRevoked());
        assertEquals(Token.TokenType.BEARER, token.getTokenType());
        assertEquals(user, token.getUser());
    }

    @Test
    void shouldThrowValidationErrorsWhenFieldsAreMissing() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Token invalidToken = Token.builder().build();

        Set<ConstraintViolation<Token>> violations = validator.validate(invalidToken);

        assertFalse(violations.isEmpty());

        boolean tokenMissing = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("token") && v.getMessage().equals("Token is required"));
        boolean tokenTypeMissing = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("tokenType") && v.getMessage().equals("TokenType is required"));
        boolean userMissing = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("user") && v.getMessage().equals("User is required"));

        assertTrue(tokenMissing);
        assertTrue(tokenTypeMissing);
        assertTrue(userMissing);
    }
}
