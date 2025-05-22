package dev.connectme.connectme.auth.application;

import dev.connectme.connectme.auth.application.ports.TokenServicePort;
import dev.connectme.connectme.auth.application.usecases.RegisterUseCase;
import dev.connectme.connectme.auth.domain.exceptions.UserAlreadyRegisteredException;
import dev.connectme.connectme.auth.infrastructure.dtos.in.RegisterUserDTORequest;
import dev.connectme.connectme.auth.infrastructure.dtos.out.RegisterUserDtoResponse;
import dev.connectme.connectme.countryCode.domain.models.CountryCode;
import dev.connectme.connectme.countryCode.domain.ports.CountryCodeRepositoryPort;
import dev.connectme.connectme.phone.domain.models.Phone;
import dev.connectme.connectme.phone.domain.ports.PhoneRepositoryPort;
import dev.connectme.connectme.user.domain.models.User;
import dev.connectme.connectme.user.domain.ports.out.UserRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class RegisterUseCaseTest {
    @Mock
    private PhoneRepositoryPort phoneRepositoryPort;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenServicePort tokenServicePort;

    @Mock
    private CountryCodeRepositoryPort countryCodeRepositoryPort;

    @Mock
    private UserRepositoryPort userRepositoryPort;

    @InjectMocks
    private RegisterUseCase registerUseCase;

    private RegisterUserDTORequest registerUserDTORequest;
    @BeforeEach
    void setUp() {
        registerUserDTORequest = RegisterUserDTORequest.builder()
            .password("5569382066")
            .countryCodeId(1L)
            .phoneNumber("5569382066")
            .email("test@test.com")
            .lastName("sanchez")
            .firstName("jose")
            .build();
    }

    @Test
    void shouldRegisterUserCorrectly() {

        CountryCode codeStored = CountryCode.builder()
                .code("+52")
                .flag("mx")
                .name("Mexico")
                .id(1L)
                .build();
        User userStored = User.builder()
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

        when(phoneRepositoryPort.findByNumber(any(String.class))).thenReturn(Optional.empty());
        when(countryCodeRepositoryPort.findById(any(Long.class))).thenReturn(Optional.ofNullable(codeStored));
        when(passwordEncoder.encode(any(String.class))).thenReturn("encodedPassword");
        when(userRepositoryPort.save(any(User.class))).thenReturn(userStored);

        RegisterUserDtoResponse userDtoResponse = registerUseCase.execute(registerUserDTORequest);

        assertNotNull(userDtoResponse);
        assertEquals(userDtoResponse.getFirstName(), registerUserDTORequest.getFirstName());
        assertEquals(userDtoResponse.getLastName(), registerUserDTORequest.getLastName());
        assertEquals(userDtoResponse.getCountryCode(), "+52");
        assertEquals(userDtoResponse.getPhoneNumber(), registerUserDTORequest.getPhoneNumber());
    }

    @Test
    void shouldThrowUserAlreadyRegisteredException() {
        CountryCode countryCode = CountryCode.builder()
                .id(1L)
                .name("Mexico")
                .flag("mx")
                .code("+52")
                .build();
        Phone phoneStored = Phone.builder()
                .number("5569382066")
                .id(1L)
                .countryCode(countryCode)
                .build();
        when(phoneRepositoryPort.findByNumber(any(String.class))).thenReturn(Optional.ofNullable(phoneStored));
        assertThrows(UserAlreadyRegisteredException.class, () -> {
            RegisterUserDtoResponse userDtoResponse = registerUseCase.execute(registerUserDTORequest);
        });
    }
}
