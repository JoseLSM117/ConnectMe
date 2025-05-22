package dev.connectme.connectme.phone.infrastructure.mapper;

import dev.connectme.connectme.countryCode.domain.models.CountryCode;
import dev.connectme.connectme.countryCode.infrastructure.entities.CountryCodeEntity;
import dev.connectme.connectme.phone.domain.models.Phone;
import dev.connectme.connectme.phone.infrastructure.entities.PhoneEntity;
import dev.connectme.connectme.user.domain.models.User;
import dev.connectme.connectme.user.infrastructure.entities.UserEntity;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class PhoneEntityMapperTest {

    @Test
    void shouldConvertToEntity() {
        CountryCode countryCode = CountryCode.builder()
                .code("+52")
                .flag("mx")
                .name("Mexico")
                .id(1L)
                .build();
        User user = User.builder()
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
        Phone phone = Phone.builder()
                .id(1L)
                .number("5569382066")
                .countryCode(countryCode)
                .user(user)
                .build();
        PhoneEntity phoneEntity = PhoneEntity.fromDomain(phone);

        assertNotNull(phoneEntity);
        assertEquals(1L, phoneEntity.getId());
        assertEquals("5569382066", phoneEntity.getNumber());

        // CountryCode assertions
        assertNotNull(phoneEntity.getCountryCode());
        assertEquals("+52", phoneEntity.getCountryCode().getCode());
        assertEquals("mx", phoneEntity.getCountryCode().getFlag());
        assertEquals("Mexico", phoneEntity.getCountryCode().getName());
        assertEquals(1L, phoneEntity.getCountryCode().getId());

        // User assertions
        assertNotNull(phoneEntity.getUser());
        assertEquals("5569382066", phoneEntity.getUser().getPassword());
        assertEquals("Mexico", phoneEntity.getUser().getCountry());
        assertEquals("test@test.com", phoneEntity.getUser().getEmail());
        assertEquals(User.Gender.MALE, phoneEntity.getUser().getGender());
        assertFalse(phoneEntity.getUser().isVerify());
        assertEquals("nose.png", phoneEntity.getUser().getProfilePicture());
        assertEquals("sanchez", phoneEntity.getUser().getLastName());
        assertEquals("jose", phoneEntity.getUser().getFirstName());
        assertNotNull(phoneEntity.getUser().getCreateAt());
        assertNotNull(phoneEntity.getUser().getUpdateAt());
    }

    @Test
    void shouldConvertToDomain() {
        CountryCodeEntity countryCode = CountryCodeEntity.builder()
                .code("+52")
                .flag("mx")
                .name("Mexico")
                .id(1L)
                .build();
        UserEntity userEntity = UserEntity.builder()
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
        PhoneEntity phoneEntity = PhoneEntity.builder()
                .id(1L)
                .number("5569382066")
                .countryCode(countryCode)
                .user(userEntity)
                .build();

        Phone phoneConverted = phoneEntity.toDomain();

        assertNotNull(phoneConverted);
        assertEquals(1L, phoneConverted.getId());
        assertEquals("5569382066", phoneConverted.getNumber());

        // CountryCode assertions
        assertNotNull(phoneConverted.getCountryCode());
        assertEquals("+52", phoneConverted.getCountryCode().getCode());
        assertEquals("mx", phoneConverted.getCountryCode().getFlag());
        assertEquals("Mexico", phoneConverted.getCountryCode().getName());
        assertEquals(1L, phoneConverted.getCountryCode().getId());

        // User assertions
        assertNotNull(phoneConverted.getUser());
        assertEquals("5569382066", phoneConverted.getUser().getPassword());
        assertEquals("Mexico", phoneConverted.getUser().getCountry());
        assertEquals("test@test.com", phoneConverted.getUser().getEmail());
        assertEquals(User.Gender.MALE, phoneConverted.getUser().getGender());
        assertFalse(phoneConverted.getUser().isVerify());
        assertEquals("nose.png", phoneConverted.getUser().getProfilePicture());
        assertEquals("sanchez", phoneConverted.getUser().getLastName());
        assertEquals("jose", phoneConverted.getUser().getFirstName());
        assertNotNull(phoneConverted.getUser().getCreateAt());
        assertNotNull(phoneConverted.getUser().getUpdateAt());
    }
    @Test
    void shouldInstanceCorrectly() {
        assertDoesNotThrow(()-> {
            PhoneEntityMapper phoneEntityMapper = new PhoneEntityMapper();
        });
    }
}
