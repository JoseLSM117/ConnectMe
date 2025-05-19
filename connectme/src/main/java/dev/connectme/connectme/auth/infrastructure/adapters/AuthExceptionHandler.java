package dev.connectme.connectme.auth.infrastructure.adapters;

import dev.connectme.connectme.auth.domain.exceptions.UserAlreadyRegisteredException;
import dev.connectme.connectme.shared.infrastructure.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthExceptionHandler {
    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<ApiResponse<Object>> handleUserAlreadyRegistered(UserAlreadyRegisteredException ex) {
        ApiResponse<Object> response = new ApiResponse<>(400, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
