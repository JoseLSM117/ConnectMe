package dev.connectme.connectme.phone.infrastructure.repositories;

import dev.connectme.connectme.countryCode.infrastructure.entities.CountryCodeEntity;
import dev.connectme.connectme.phone.domain.models.Phone;
import dev.connectme.connectme.phone.infrastructure.entities.PhoneEntity;
import dev.connectme.connectme.user.domain.models.User;
import dev.connectme.connectme.user.infrastructure.entities.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class JpaPhoneRepositoryAdapterTest {
    @Mock
    private JpaPhoneRepository jpaPhoneRepository;

    @InjectMocks
    private JpaPhoneRepositoryAdapter jpaPhoneRepositoryAdapter;

    private CountryCodeEntity countryCodeEntity;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        countryCodeEntity = CountryCodeEntity.builder()
                .id(1L)
                .name("Mexico")
                .flag("mx")
                .code("+52")
                .build();
        userEntity = UserEntity.builder()
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
    }

    @Test
    void shouldSaveCorrectlyPhone() {
        Phone phoneToSave = Phone.builder()
                .id(1L)
                .number("5569382066")
                .countryCode(countryCodeEntity.toDomainModel())
                .user(userEntity.toDomainModel())
                .build();

        PhoneEntity phoneEntitySaved = PhoneEntity.builder()
                .id(1L)
                .number("5569382066")
                .countryCode(countryCodeEntity)
                .user(userEntity)
                .build();
        when(jpaPhoneRepository.save(any(PhoneEntity.class))).thenReturn(phoneEntitySaved);

        Phone phoneSaved = jpaPhoneRepositoryAdapter.save(phoneToSave);

        assertNotNull(phoneSaved);
        assertEquals(1L, phoneSaved.getId());
        assertEquals("5569382066", phoneSaved.getNumber());

        assertNotNull(phoneSaved.getCountryCode());
        assertEquals("Mexico", phoneSaved.getCountryCode().getName());
        assertEquals("mx", phoneSaved.getCountryCode().getFlag());
        assertEquals("+52", phoneSaved.getCountryCode().getCode());

        assertNotNull(phoneSaved.getUser());
        assertEquals("jose", phoneSaved.getUser().getFirstName());
        assertEquals("sanchez", phoneSaved.getUser().getLastName());
        assertEquals("test@test.com", phoneSaved.getUser().getEmail());
        assertEquals(User.Gender.MALE, phoneSaved.getUser().getGender());
        assertEquals("Mexico", phoneSaved.getUser().getCountry());
        assertEquals("nose.png", phoneSaved.getUser().getProfilePicture());
        assertEquals("5569382066", phoneSaved.getUser().getPassword());
        assertFalse(phoneSaved.getUser().isVerify());
        assertNotNull(phoneSaved.getUser().getCreateAt());
        assertNotNull(phoneSaved.getUser().getUpdateAt());
    }

    @Test
    void shouldFindPhoneByNumber() {
        PhoneEntity phoneEntitySaved = PhoneEntity.builder()
                .id(1L)
                .number("5569382066")
                .countryCode(countryCodeEntity)
                .user(userEntity)
                .build();
        when(jpaPhoneRepository.findByNumber(any(String.class))).thenReturn(Optional.ofNullable(phoneEntitySaved));

        Phone phoneFound = jpaPhoneRepositoryAdapter.findByNumber("5569382066").orElseThrow();

        assertNotNull(phoneFound);
        assertEquals(1L, phoneFound.getId());
        assertEquals("5569382066", phoneFound.getNumber());

        assertNotNull(phoneFound.getCountryCode());
        assertEquals("Mexico", phoneFound.getCountryCode().getName());
        assertEquals("mx", phoneFound.getCountryCode().getFlag());
        assertEquals("+52", phoneFound.getCountryCode().getCode());

        assertNotNull(phoneFound.getUser());
        assertEquals("jose", phoneFound.getUser().getFirstName());
        assertEquals("sanchez", phoneFound.getUser().getLastName());
        assertEquals("test@test.com", phoneFound.getUser().getEmail());
        assertEquals(User.Gender.MALE, phoneFound.getUser().getGender());
        assertEquals("Mexico", phoneFound.getUser().getCountry());
        assertEquals("nose.png", phoneFound.getUser().getProfilePicture());
        assertEquals("5569382066", phoneFound.getUser().getPassword());
        assertFalse(phoneFound.getUser().isVerify());
        assertNotNull(phoneFound.getUser().getCreateAt());
        assertNotNull(phoneFound.getUser().getUpdateAt());
    }
}
