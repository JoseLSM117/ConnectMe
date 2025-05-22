package dev.connectme.connectme.countryCode.infrastructure.dtos.in;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.internal.engine.groups.ValidationOrder;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
public class ToStoreCountryCodeDtoTest {
    @Test
    void shouldInstanceCorrectly() {
        String name = "Mexico";
        String code = "+52";
        String flag = "mx";
        ToStoreCountryCodeDto toStoreCountryCodeDto = new ToStoreCountryCodeDto(name, code, flag);

        assertNotNull(toStoreCountryCodeDto);
        assertEquals(name, toStoreCountryCodeDto.getName());
        assertEquals(code, toStoreCountryCodeDto.getCode());
        assertEquals(flag, toStoreCountryCodeDto.getFlag());
    }

    @Test
    void shouldThrowErrorIfNameIsNotPresent() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        ToStoreCountryCodeDto toStoreCountryCodeDto = ToStoreCountryCodeDto.builder()
                .code("+52")
                .flag("mx")
                .build();

        Set<ConstraintViolation<ToStoreCountryCodeDto>> violations = validator.validate(toStoreCountryCodeDto);

        assertFalse(violations.isEmpty());

        boolean nameIsNotPresent = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("name") && v.getMessage().equals("Country name is required"));

        assertTrue(nameIsNotPresent);
    }

    @Test
    void shouldThrowErrorIfCodeIsNotPresent() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        ToStoreCountryCodeDto toStoreCountryCodeDto = ToStoreCountryCodeDto.builder()
                .flag("mx")
                .name("Mexico")
                .build();
        Set<ConstraintViolation<ToStoreCountryCodeDto>> violations = validator.validate(toStoreCountryCodeDto);
        assertFalse(violations.isEmpty());

        boolean codeIsNotPresent = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("code") && v.getMessage().equals("Country code is required"));

        assertTrue(codeIsNotPresent);
    }

    @Test
    void shouldThrowErrorIfCodeIsLessThan2() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        ToStoreCountryCodeDto toStoreCountryCodeDto = ToStoreCountryCodeDto.builder()
                .name("Mexico")
                .flag("mx")
                .code("1")
                .build();
        Set<ConstraintViolation<ToStoreCountryCodeDto>> violations = validator.validate(toStoreCountryCodeDto);

        assertFalse(violations.isEmpty());

        boolean codeIsLessThan2 = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("code") && v.getMessage().equals("Country code must be between 2 and 4 chars (e.g., +1, +52)"));

        assertTrue(codeIsLessThan2);
    }

    @Test
    void shouldThrowErrorIfCodeIsBiggerThan4() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        ToStoreCountryCodeDto toStoreCountryCodeDto = ToStoreCountryCodeDto.builder()
                .name("Mexico")
                .flag("mx")
                .code("+5252")
                .build();

        Set<ConstraintViolation<ToStoreCountryCodeDto>> violations = validator.validate(toStoreCountryCodeDto);

        assertFalse(violations.isEmpty());

        boolean codeIsBiggerThan4 = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("code") && v.getMessage().equals("Country code must be between 2 and 4 chars (e.g., +1, +52)"));

        assertTrue(codeIsBiggerThan4);
    }

    @Test
    void shouldThrowErrorIfCodePatterIsWrong() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        ToStoreCountryCodeDto toStoreCountryCodeDto = ToStoreCountryCodeDto.builder()
                .name("Mexico")
                .flag("mx")
                .code("52")
                .build();

        Set<ConstraintViolation<ToStoreCountryCodeDto>> violations = validator.validate(toStoreCountryCodeDto);

        assertFalse(violations.isEmpty());

        boolean codePatternIsWrong = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("code") && v.getMessage().equals("Invalid country code (must start with '+' and have 1-3 digits)"));

        assertTrue(codePatternIsWrong);
    }
}
