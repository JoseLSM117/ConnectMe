package dev.connectme.connectme.countryCode.infrastructure.adapters;

import dev.connectme.connectme.countryCode.domain.exceptions.CountryCodeAlreadyExistException;
import dev.connectme.connectme.shared.infrastructure.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CountryCodeExceptionHandler {
    @ExceptionHandler(CountryCodeAlreadyExistException.class)
    public ResponseEntity<ApiResponse<Object>> countryCodeAlreadyExist(CountryCodeAlreadyExistException ex) {
        ApiResponse<Object> response = new ApiResponse<>(400, ex.getMessage(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
