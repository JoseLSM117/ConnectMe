package dev.connectme.connectme.shared.infrastructure.adapters;

import dev.connectme.connectme.shared.infrastructure.responses.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

public class GlobalExceptionHandlerTest {
    @Test
    void shouldHandleValidationExceptionAndReturnBadRequest() throws NoSuchMethodException {
        // Arrange
        GlobalExceptionHandler handler = new GlobalExceptionHandler();

        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError1 = new FieldError("object", "email", "Email is invalid");
        FieldError fieldError2 = new FieldError("object", "password", "Password is too short");

        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError1, fieldError2));

        Method method = this.getClass().getDeclaredMethod("dummyMethod");
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, bindingResult);

        // Act
        ResponseEntity<ApiResponse<Map<String, String>>> response = handler.handleValidationExceptions(ex);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        ApiResponse<Map<String, String>> body = response.getBody();
        assertNotNull(body);
        assertEquals(400, body.getStatus());
        assertEquals("Validation failed", body.getMessage());
        assertEquals(2, body.getBody().size());
        assertEquals("Email is invalid", body.getBody().get("email"));
        assertEquals("Password is too short", body.getBody().get("password"));
    }

    private void dummyMethod() {}
}
