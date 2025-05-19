package dev.connectme.connectme.userStatus.domain.models;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class UserStatusTest {

    @Test
    void shouldCreateUserStatusCorrectly() {
        UserStatus userStatus = UserStatus.builder()
                .id(1L)
                .statusName(UserStatus.StatusName.Inactive)
                .lastSeen(new Date())
                .build();

        assertNotNull(userStatus);
        assertEquals(userStatus.getId(), 1L);
        assertEquals(userStatus.getStatusName(), UserStatus.StatusName.Inactive);
    }

    @Test
    void shouldThrowErrorIfIdIsNotPresent() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        UserStatus userStatus = UserStatus.builder()
                .statusName(UserStatus.StatusName.Inactive)
                .lastSeen(new Date())
                .build();

        Set<ConstraintViolation<UserStatus>> violations = validator.validate(userStatus);

        assertFalse(violations.isEmpty());

        boolean idErrorFound = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("id")
                        && v.getMessage().equals("ID cannot be null"));

        assertTrue(idErrorFound);
    }
}
