package dev.connectme.connectme.phone.domain.models;

import dev.connectme.connectme.countryCode.domain.models.CountryCode;
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
public class PhoneTest {

    private User user;
    private CountryCode countryCode;

    @BeforeEach
    void setUp(){
        user = User.builder()
                .id(1L)
                .password("5569382066")
                .country("Mexico")
                .email("test@test.com")
                .gender(User.Gender.MALE)
                .isVerify(false)
                .createAt(new Date())
                .updateAt(new Date())
                .profilePicture("nose.png")
                .lastName("sanchez")
                .firstName("jose")
                .build();
        countryCode = CountryCode.builder()
                .id(1L)
                .code("+52")
                .flag("mx")
                .name("Mexico")
                .build();
    }

    @Test
    void shouldInstanceCorrectly() {

        Phone phone = Phone.builder()
                .id(1L)
                .countryCode(countryCode)
                .number("5569382066")
                .user(user)
                .build();

       assertNotNull(phone);
       assertEquals(phone.getId(), 1L);
       assertEquals(phone.getNumber(), "5569382066");
       assertEquals(phone.getCountryCode().getId(), 1L);
       assertEquals(phone.getUser().getId(), 1L);
    }

    @Test
    void shouldThrowErrorsForAllRequiredFields() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Phone phone = Phone.builder().build();

        Set<ConstraintViolation<Phone>> violations = validator.validate(phone);

        assertFalse(violations.isEmpty());

        boolean idIsNull = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("id") && v.getMessage().equals("Phone id is required"));
        boolean countryCodeIsNull = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("countryCode") && v.getMessage().equals("Country Code is required"));
        boolean numberIsNull = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("number") && v.getMessage().equals("Phone number is required"));
        boolean userIsNull = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("user") && v.getMessage().equals("User is required in phone entity"));

        assertTrue(idIsNull);
        assertTrue(countryCodeIsNull);
        assertTrue(numberIsNull);
        assertTrue(userIsNull);
    }

    @Test
    void shouldThrowErrorIfNumberSizeIsNotBetween4and15() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Phone phoneLessThan4 = Phone.builder()
                .id(1L)
                .countryCode(countryCode)
                .number("555")
                .user(user)
                .build();
        Phone phoneBiggerThan15 = Phone.builder()
                .id(1L)
                .countryCode(countryCode)
                .number("5569382066123456789")
                .user(user)
                .build();
        Set<ConstraintViolation<Phone>> violationsPhoneLessThan4 = validator.validate(phoneLessThan4);
        Set<ConstraintViolation<Phone>> violationPhoneBiggerThan15 = validator.validate(phoneBiggerThan15);

        assertFalse(violationsPhoneLessThan4.isEmpty());
        assertFalse(violationPhoneBiggerThan15.isEmpty());

        boolean numberLessThan4 = violationsPhoneLessThan4.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("number") && v.getMessage().equals("Phone number must be between 4 and 15 digits"));
        boolean numberBiggerThan15 = violationsPhoneLessThan4.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("number") && v.getMessage().equals("Phone number must be between 4 and 15 digits"));

        assertTrue(numberLessThan4);
        assertTrue(numberBiggerThan15);
    }
}
