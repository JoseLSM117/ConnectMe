package dev.connectme.connectme.auth.infrastructure.dtos.in;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
public class RegisterUserDTORequestTest {

    @Test
    void shouldInstanceCorrectly() {
        RegisterUserDTORequest request = RegisterUserDTORequest.builder()
                .firstName("Juan")
                .lastName("Perez")
                .email("juan@example.com")
                .password("Secure123")
                .countryCodeId(1L)
                .phoneNumber("5569382066")
                .build();

        assertNotNull(request);
        assertEquals("Juan", request.getFirstName());
        assertEquals("Perez", request.getLastName());
        assertEquals("juan@example.com", request.getEmail());
        assertEquals("Secure123", request.getPassword());
        assertEquals(1L, request.getCountryCodeId());
        assertEquals("5569382066", request.getPhoneNumber());
    }

    @Test
    void shouldThrowErrorsForAllRequiredFields() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        RegisterUserDTORequest request = new RegisterUserDTORequest();

        Set<ConstraintViolation<RegisterUserDTORequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());

        boolean firstNameIsBlank = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("firstName")
                        && v.getMessage().equals("First name is required."));
        boolean lastNameIsBlank = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("lastName")
                        && v.getMessage().equals("Last name is required."));
        boolean passwordIsBlank = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("password")
                        && v.getMessage().equals("Password is required."));
        boolean phoneNumberIsBlank = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("phoneNumber")
                        && v.getMessage().equals("Phone number is required"));

        assertTrue(firstNameIsBlank);
        assertTrue(lastNameIsBlank);
        assertTrue(passwordIsBlank);
        assertTrue(phoneNumberIsBlank);
    }

    @Test
    void shouldValidateFieldSizes() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        // First name too short
        RegisterUserDTORequest shortFirstName = RegisterUserDTORequest.builder()
                .firstName("J")
                .lastName("Perez")
                .password("123456")
                .phoneNumber("1234")
                .build();

        // First name too long
        RegisterUserDTORequest longFirstName = RegisterUserDTORequest.builder()
                .firstName("J".repeat(51))
                .lastName("Perez")
                .password("123456")
                .phoneNumber("1234")
                .build();

        // Password too short
        RegisterUserDTORequest shortPassword = RegisterUserDTORequest.builder()
                .firstName("Juan")
                .lastName("Perez")
                .password("12345")
                .phoneNumber("1234")
                .build();

        Set<ConstraintViolation<RegisterUserDTORequest>> violations = validator.validate(shortFirstName);
        violations.addAll(validator.validate(longFirstName));
        violations.addAll(validator.validate(shortPassword));

        assertFalse(violations.isEmpty());

        boolean firstNameSizeViolation = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("firstName")
                        && v.getMessage().equals("First name must be between 2 and 50 characters."));
        boolean passwordSizeViolation = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("password")
                        && v.getMessage().equals("Password must be between 6 and 100 characters."));

        assertTrue(firstNameSizeViolation);
        assertTrue(passwordSizeViolation);
    }

    @Test
    void shouldValidatePhoneNumberPattern() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        RegisterUserDTORequest invalidPhone = RegisterUserDTORequest.builder()
                .firstName("Juan")
                .lastName("Perez")
                .password("Secure123")
                .phoneNumber("55-693-820")
                .build();

        Set<ConstraintViolation<RegisterUserDTORequest>> violations = validator.validate(invalidPhone);

        assertFalse(violations.isEmpty());

        boolean phonePatternViolation = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("phoneNumber")
                        && v.getMessage().equals("Phone number must contain only digits (no spaces or symbols)"));

        assertTrue(phonePatternViolation);
    }

    @Test
    void shouldValidateEmailFormat() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        RegisterUserDTORequest invalidEmail = RegisterUserDTORequest.builder()
                .firstName("Juan")
                .lastName("Perez")
                .email("invalid-email")
                .password("Secure123")
                .phoneNumber("5569382066")
                .build();

        Set<ConstraintViolation<RegisterUserDTORequest>> violations = validator.validate(invalidEmail);

        assertFalse(violations.isEmpty());

        boolean emailViolation = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("email")
                        && v.getMessage().equals("Please provide a valid email address"));

        assertTrue(emailViolation);
    }
}