package dev.connectme.connectme.auth.domain.exceptions;

import dev.connectme.connectme.countryCode.domain.exceptions.CountryCodeAlreadyExistException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserAlreadyRegisteredExceptionTest {

    @Test
    void shouldThrowUserAlreadyRegisteredExceptionWithOutMessage() {
        CountryCodeAlreadyExistException exception = new CountryCodeAlreadyExistException();
        assertNull(exception.getMessage());
    }

    @Test
    void shouldThrowUserAlreadyRegisteredExceptionWithMessage() {
        String message = "User already registered";
        CountryCodeAlreadyExistException exception = new CountryCodeAlreadyExistException(message);
        assertEquals("User already registered", exception.getMessage());
    }
}
