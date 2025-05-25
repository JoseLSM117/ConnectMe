package dev.connectme.connectme.user.domain.models;

import dev.connectme.connectme.auth.domain.models.Token;
import dev.connectme.connectme.countryCode.domain.models.CountryCode;
import dev.connectme.connectme.phone.domain.models.Phone;
import dev.connectme.connectme.userStatus.domain.models.UserStatus;
import jakarta.validation.*;
import org.junit.jupiter.api.Test;
import org.mockito.exceptions.verification.VerificationInOrderFailure;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class UserTest {

    @Test
    void shouldInstanceCorrectly() {
        CountryCode countryCode = CountryCode.builder()
                .name("Mexico")
                .code("+52")
                .flag("mx")
                .build();
        Phone phone = Phone.builder()
                .id(1L)
                .number("5569382066")
                .countryCode(countryCode)
                .build();
        Token token1 = Token.builder()
                .tokenType(Token.TokenType.BEARER)
                .token("test")
                .revoked(false)
                .expired(false)
                .build();
        UserStatus userStatus = UserStatus.builder()
                .lastSeen(new Date())
                .statusName(UserStatus.StatusName.Active)
                .id(1L)
                .build();
        User user = User.builder()
                .id(1L)
                .phone(phone)
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
                .tokens(List.of(token1))
                .userStatus(userStatus)
                .build();

        assertNotNull(user);
        assertEquals(user.getPhone().getId(), phone.getId());
        assertEquals(user.getUserStatus().getStatusName(), userStatus.getStatusName());
        assertEquals(user.getUserStatus().getId(), user.getId());
        assertEquals(user.getTokens().get(0).getId(), token1.getId());
        assertEquals(user.getTokens().size(), 1);
    }

    @Test
    void shouldThrowErrorIfRequiredFieldsIsNull() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        User user = User.builder()
                .id(1L)
                .country("Mexico")
                .email("test@test.com")
                .gender(User.Gender.MALE)
                .createAt(new Date())
                .updateAt(new Date())
                .profilePicture("nose.png")
                .build();

        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertFalse(violations.isEmpty());

        boolean firstnameIsNull = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("firstName") && v.getMessage().equals("First name is required"));
        boolean lastNameIsNull = violations.stream()
                        .anyMatch(v -> v.getPropertyPath().toString().equals("lastName") && v.getMessage().equals("Last name is required"));
        boolean passwordIsNull = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("password") && v.getMessage().equals("Password is required"));

        assertTrue(firstnameIsNull, "First name is null");
        assertTrue(lastNameIsNull, "Last name is null");
        assertTrue(passwordIsNull);
    }

    @Test
    void shouldAssignDefaultValueInIsVerifyProperty() {
        User user = User.builder()
                .id(1L)
                .country("Mexico")
                .email("test@test.com")
                .gender(User.Gender.MALE)
                .createAt(new Date())
                .updateAt(new Date())
                .profilePicture("nose.png")
                .firstName("Jose")
                .lastName("Sanchez")
                .password("5569392066")
                .build();
        assertFalse(user.isVerify());
    }

    @Test
    void shouldThrowErrorIfFirstNameIsBiggerThan50AndLessThan2() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        User user = User.builder()
                .id(1L)
                .country("Mexico")
                .email("test@test.com")
                .gender(User.Gender.MALE)
                .createAt(new Date())
                .updateAt(new Date())
                .profilePicture("nose.png")
                .firstName("Juan Carlos Alejandro Maximiliano De La Torre Sánchez Jimenez")
                .lastName("Sanchez")
                .password("5569392066")
                .build();

        User user2 = User.builder()
                .id(1L)
                .country("Mexico")
                .email("test@test.com")
                .gender(User.Gender.MALE)
                .createAt(new Date())
                .updateAt(new Date())
                .profilePicture("nose.png")
                .firstName("X")
                .lastName("Sanchez")
                .password("5569392066")
                .build();

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        Set<ConstraintViolation<User>> violations2 = validator.validate(user2);

        assertFalse(violations.isEmpty());
        assertFalse(violations2.isEmpty());

        boolean firstNameIsBiggerThan50 = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("firstName") && v.getMessage().equals("First name must be between 2 and 50 characters"));
        boolean firstNameIsLessThan2 = violations2.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("firstName") && v.getMessage().equals("First name must be between 2 and 50 characters"));

        assertTrue(firstNameIsBiggerThan50);
        assertTrue(firstNameIsLessThan2);
    }

    @Test
    void shouldThrowErrorIfLastNameIsBiggerThan50AndLessThan2() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        User user = User.builder()
                .id(1L)
                .country("Mexico")
                .email("test@test.com")
                .gender(User.Gender.MALE)
                .createAt(new Date())
                .updateAt(new Date())
                .profilePicture("nose.png")
                .firstName("Jose")
                .lastName("Juan Carlos Alejandro Maximiliano De La Torre Sánchez Jimenez")
                .password("5569392066")
                .build();

        User user2 = User.builder()
                .id(1L)
                .country("Mexico")
                .email("test@test.com")
                .gender(User.Gender.MALE)
                .createAt(new Date())
                .updateAt(new Date())
                .profilePicture("nose.png")
                .firstName("Jose")
                .lastName("X")
                .password("5569392066")
                .build();

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        Set<ConstraintViolation<User>> violations2 = validator.validate(user2);

        assertFalse(violations.isEmpty());
        assertFalse(violations2.isEmpty());

        boolean lastNameIsBiggerThan50 = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("lastName") && v.getMessage().equals("Last name must be between 2 and 50 characters"));
        boolean lastNameIsLessThan2 = violations2.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("lastName") && v.getMessage().equals("Last name must be between 2 and 50 characters"));

        assertTrue(lastNameIsBiggerThan50);
        assertTrue(lastNameIsLessThan2);
    }

    @Test
    void shouldThrowErrorIfEmailNotMeetTheConditions() {
        // Arrange
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        User user = User.builder()
                .id(1L)
                .country("Mexico")
                .email("test.com")
                .gender(User.Gender.MALE)
                .createAt(new Date())
                .updateAt(new Date())
                .profilePicture("nose.png")
                .firstName("Jose")
                .lastName("Sanchez")
                .password("5569392066")
                .build();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertFalse(violations.isEmpty());

        boolean emailIsNotValid = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("email") && v.getMessage().equals("Please provide a valid email address"));
        assertTrue(emailIsNotValid);
    }

    @Test
    void shouldThrowErrorIfProfilePictureIsBiggerThan255() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        User user = User.builder()
                .id(1L)
                .profilePicture("https://ejemplo.com/ruta/ABCDEFGHIJKLMNOPQRSTUVWXYZ/0987654321/zyxwvutsrqponmlkjihgfedcba/este/es/un/ejemplo/de/una/url/muy/ABCDEFGHIJKLMNOPQRSTUVWXYZ/0987654321/zyxwvutsrqponmlkjihgfedcba/este/es/un/ejemplo/de/una/url/muy/ABCDEFGHIJKLMNOPQRSTUVWXYZ/0987654321/zyxwvutsrqponmlkjihgfedcba/este/es/un/ejemplo/de/una/url/muy/mas-larga-xd/muy/larga/para/que/la/url/supere/los/255/caracteres/abcdefghijklmnopqrstuvwxyz/1234567890/ABCDEFGHIJKLMNOPQRSTUVWXYZ/0987654321/zyxwvutsrqponmlkjihgfedcba/este/es/un/ejemplo/de/una/url/muy/larga/para/probar/el/limite/de/longitud/en/sistemas/que/tienen/restricciones/de/tamano/profilePicture(\"nose.png\")")
                .country("Mexico")
                .email("test@test.com")
                .gender(User.Gender.MALE)
                .createAt(new Date())
                .updateAt(new Date())
                .firstName("Jose")
                .lastName("Sanchez")
                .password("5569392066")
                .build();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertFalse(violations.isEmpty());

        boolean profilePictureIsBiggerThan255 = violations.stream()
                .anyMatch(v -> v.getPropertyPath().toString().equals("profilePicture") && v.getMessage().equals("Profile picture URL is too long"));

        assertTrue(profilePictureIsBiggerThan255);
    }

    @Test
    void shouldAddTokenWithTheMethod() {
        Token token1 = Token.builder()
                .tokenType(Token.TokenType.BEARER)
                .token("test")
                .revoked(false)
                .expired(false)
                .build();
        User user = User.builder()
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
                .tokens(new ArrayList<>(List.of(token1)))
                .build();

        assertEquals(user.getTokens().size(), 1);
        assertEquals(user.getTokens().get(0).getToken(), "test");
    }
}
