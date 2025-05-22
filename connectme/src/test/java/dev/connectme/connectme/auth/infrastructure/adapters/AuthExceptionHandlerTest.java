package dev.connectme.connectme.auth.infrastructure.adapters;

import dev.connectme.connectme.auth.domain.exceptions.UserAlreadyRegisteredException;
import dev.connectme.connectme.shared.infrastructure.responses.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.*;

public class AuthExceptionHandlerTest {
    @Test
    void shouldHandleValidationExceptionAndReturnBadRequest() {
        AuthExceptionHandler handler = new AuthExceptionHandler();

        UserAlreadyRegisteredException ex = new UserAlreadyRegisteredException();

        ResponseEntity<ApiResponse<Object>> response = handler.handleUserAlreadyRegistered(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(400, response.getBody().getStatus());
        assertEquals("The phone number is already registered", response.getBody().getMessage());
        assertNull(response.getBody().getBody());
    }

    @Test
    void shouldHandleValidationExceptionAndReturnBadRequestWithCustomMessage() {
        AuthExceptionHandler handler = new AuthExceptionHandler();
        String message = "Test";
        UserAlreadyRegisteredException ex = new UserAlreadyRegisteredException(message);

        ResponseEntity<ApiResponse<Object>> response = handler.handleUserAlreadyRegistered(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(400, response.getBody().getStatus());
        assertEquals("Test", response.getBody().getMessage());
        assertNull(response.getBody().getBody());
    }
}
