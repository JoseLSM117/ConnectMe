package dev.connectme.connectme.phone.infrastructure.entities;

import dev.connectme.connectme.countryCode.domain.models.CountryCode;
import dev.connectme.connectme.countryCode.infrastructure.entities.CountryCodeEntity;
import dev.connectme.connectme.countryCode.infrastructure.repositories.JpaCountryCodeRepository;
import dev.connectme.connectme.phone.domain.models.Phone;
import dev.connectme.connectme.phone.infrastructure.repositories.JpaPhoneRepository;
import dev.connectme.connectme.user.domain.models.User;
import dev.connectme.connectme.user.infrastructure.entities.UserEntity;
import dev.connectme.connectme.user.infrastructure.repositories.JpaUserRepository;
import dev.connectme.connectme.userStatus.domain.models.UserStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class PhoneEntityTest {

    private UserEntity user;
    private CountryCodeEntity countryCodeEntity;

    @Autowired
    private JpaPhoneRepository jpaPhoneRepository;

    @Autowired
    private JpaCountryCodeRepository jpaCountryCodeRepository;

    @Autowired
    private JpaUserRepository jpaUserRepository;

    @BeforeEach
    void setUp(){
        user = UserEntity.builder()
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
        jpaUserRepository.save(user);
        countryCodeEntity = CountryCodeEntity.builder()
                .code("+52")
                .flag("mx")
                .name("Mexico")
                .build();
        jpaCountryCodeRepository.save(countryCodeEntity);
    }

    @Test
    void shouldInstanceCorrectly() {
        PhoneEntity phoneEntity = PhoneEntity.builder()
                .countryCode(countryCodeEntity)
                .number("5569382066")
                .user(user)
                .build();

        assertNotNull(phoneEntity);
        assertEquals(phoneEntity.getNumber(), "5569382066");
        assertEquals(phoneEntity.getUser().getGender(), User.Gender.MALE);
        assertEquals(phoneEntity.getUser().getCountry(), "Mexico");
        assertEquals(phoneEntity.getCountryCode().getCode(), "+52");
        assertEquals(phoneEntity.getCountryCode().getFlag(), "mx");
        assertEquals(phoneEntity.getCountryCode().getName(), "Mexico");
        assertNotNull(phoneEntity.getUser().getId());
        assertEquals(phoneEntity.getUser().getEmail(), "test@test.com");
        assertFalse(phoneEntity.getUser().isVerify());
        assertEquals(phoneEntity.getUser().getProfilePicture(), "nose.png");
        assertEquals(phoneEntity.getUser().getLastName(), "sanchez");
        assertEquals(phoneEntity.getUser().getFirstName(), "jose");
        assertNotNull(phoneEntity.getUser().getCreateAt());
        assertNotNull(phoneEntity.getUser().getUpdateAt());
    }

    @Test
    void shouldThrowExceptionIfCountryCodeIsNull() {
        assertThrows(Exception.class, () -> {
            PhoneEntity phoneEntity = PhoneEntity.builder()
                    .number("5569382066")
                    .user(user)
                    .build();
            jpaPhoneRepository.save(phoneEntity);
        });
    }

    @Test
    void shouldThrowExceptionIfUserIsNull() {
        assertThrows(Exception.class, () -> {
            PhoneEntity phoneEntity = PhoneEntity.builder()
                    .countryCode(countryCodeEntity)
                    .number("5569382066")
                    .build();
            jpaPhoneRepository.save(phoneEntity);
        });
    }

    @Test
    void shouldThrowExceptionIfNumberIsNull() {
        assertThrows(Exception.class, () -> {
            PhoneEntity phoneEntity = PhoneEntity.builder()
                    .countryCode(countryCodeEntity)
                    .user(user)
                    .build();
            jpaPhoneRepository.save(phoneEntity);
        });
    }

    @Test
    void shouldConvertToDomain() {
        PhoneEntity phoneEntity = PhoneEntity.builder()
                .countryCode(countryCodeEntity)
                .user(user)
                .number("5569382066")
                .build();
        jpaPhoneRepository.save(phoneEntity);
        Phone phoneConverted = phoneEntity.toDomain();

        assertNotNull(phoneConverted);
        assertEquals(phoneConverted.getNumber(), "5569382066");
        assertEquals(phoneConverted.getCountryCode().getCode(), "+52");
        assertEquals(phoneConverted.getCountryCode().getFlag(), "mx");
        assertEquals(phoneConverted.getCountryCode().getName(), "Mexico");
        assertEquals(phoneConverted.getUser().getGender(), User.Gender.MALE);
        assertEquals(phoneConverted.getUser().getCountry(), "Mexico");
        assertEquals(phoneConverted.getUser().getEmail(), "test@test.com");
        assertFalse(phoneConverted.getUser().isVerify());
        assertEquals(phoneConverted.getUser().getProfilePicture(), "nose.png");
        assertEquals(phoneConverted.getUser().getLastName(), "sanchez");
        assertEquals(phoneConverted.getUser().getFirstName(), "jose");
        assertNotNull(phoneConverted.getUser().getCreateAt());
        assertNotNull(phoneConverted.getUser().getUpdateAt());
    }

    @Test
    void shouldConvertToEntity() {
        CountryCode countryCode = CountryCode.builder()
                .id(1L)
                .name("Mexico")
                .flag("mx")
                .code("+52")
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
                .build();
        Phone phone = Phone.builder()
                .id(1L)
                .countryCode(countryCode)
                .user(user)
                .number("5569382066")
                .build();
        PhoneEntity phoneEntity = PhoneEntity.fromDomain(phone);

        assertNotNull(phoneEntity);
        assertEquals(phoneEntity.getCountryCode().getCode(), countryCode.getCode());
        assertEquals(phoneEntity.getCountryCode().getName(), countryCode.getName());
        assertEquals(phoneEntity.getCountryCode().getFlag(), countryCode.getFlag());
        assertEquals(phoneEntity.getNumber(), phone.getNumber());
        assertEquals(phoneEntity.getId(), phone.getId());

        assertEquals(phoneEntity.getUser().getId(), user.getId());
        assertEquals(phoneEntity.getUser().getEmail(), user.getEmail());
        assertEquals(phoneEntity.getUser().getFirstName(), user.getFirstName());
        assertEquals(phoneEntity.getUser().getLastName(), user.getLastName());
        assertEquals(phoneEntity.getUser().getCountry(), user.getCountry());
        assertEquals(phoneEntity.getUser().getGender(), user.getGender());
        assertEquals(phoneEntity.getUser().getProfilePicture(), user.getProfilePicture());
        assertEquals(phoneEntity.getUser().isVerify(), user.isVerify());
        assertNotNull(phoneEntity.getUser().getCreateAt());
        assertNotNull(phoneEntity.getUser().getUpdateAt());
    }
}
