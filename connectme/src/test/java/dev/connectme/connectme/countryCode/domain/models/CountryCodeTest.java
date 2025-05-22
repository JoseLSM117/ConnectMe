package dev.connectme.connectme.countryCode.domain.models;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.apache.tomcat.util.bcel.Const;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
public class CountryCodeTest {

    @Test
    void shouldCreateCountryCodeCorrectly() {
        Long id = 1L;
        String code = "+52";
        String flag = "mx";
        String name = "Mexico";
        CountryCode countryCode = CountryCode.builder()
                .id(id)
                .code(code)
                .flag(flag)
                .name(name)
                .build();
        assertNotNull(countryCode);
        assertEquals(id, countryCode.getId());
        assertEquals(code, countryCode.getCode());
        assertEquals(flag, countryCode.getFlag());
        assertEquals(name, countryCode.getName());
    }

    @Test
    void shouldThrowErrorIfNameIsNull() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        CountryCode countryCode = CountryCode.builder()
                .id(1L)
                .code("+52")
                .flag("mx")
                .build();
        Set<ConstraintViolation<CountryCode>> violations = validator.validate(countryCode);

        assertFalse(violations.isEmpty());

        boolean nameErrorFound = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("name") && v.getMessage().equals("Country name is required"));
        assertTrue(nameErrorFound);
    }

    @Test
    void shouldThrowErrorIfCodeIsNull(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        CountryCode countryCode = CountryCode.builder()
                .id(1L)
                .name("Mexico")
                .flag("mx")
                .build();
        Set<ConstraintViolation<CountryCode>> violations = validator.validate(countryCode);

        assertFalse(violations.isEmpty());

        boolean codeErrorFound = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("code") && v.getMessage().equals("Country code is required"));

        assertTrue(codeErrorFound);
    }

    @Test
    void shouldThrowErrorIfCodeSizeIsBiggerThan4() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        CountryCode countryCode = CountryCode.builder()
                .id(1L)
                .name("Mexico")
                .flag("mx")
                .code("+5454")
                .build();
        Set<ConstraintViolation<CountryCode>> violations = validator.validate(countryCode);

        assertFalse(violations.isEmpty());

        boolean codeIsBiggerThan4 = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("code") && v.getMessage().equals("Country code must be between 2 and 4 chars (e.g., +1, +52)"));

        assertTrue(codeIsBiggerThan4);
    }

    @Test
    void shouldThrowErrorIfCodeSizeIsLessThan2(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        CountryCode countryCode = CountryCode.builder()
                .id(1L)
                .code("+")
                .flag("mx")
                .name("Mexico")
                .build();

        Set<ConstraintViolation<CountryCode>> violations = validator.validate(countryCode);

        assertFalse(violations.isEmpty());

        boolean codeSizeIsLessThan2 = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("code") && v.getMessage().equals("Country code must be between 2 and 4 chars (e.g., +1, +52)"));
        assertTrue(codeSizeIsLessThan2);
    }

    @Test
    void shouldThrowErrorIfCodePatternIsWrong() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        CountryCode countryCode = CountryCode.builder()
                .id(1L)
                .name("Mexico")
                .flag("mx")
                .code("52")
                .build();
        Set<ConstraintViolation<CountryCode>> violations = validator.validate(countryCode);

        assertFalse(violations.isEmpty());

        boolean codePatterIsWrong = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("code") && v.getMessage().equals("Invalid country code (must start with '+' and have 1-3 digits)"));

        assertTrue(codePatterIsWrong);
    }
}
