package dev.connectme.connectme.user.infrastructure.mapper;

import dev.connectme.connectme.auth.domain.models.Token;
import dev.connectme.connectme.auth.infrastructure.entities.TokenEntity;
import dev.connectme.connectme.countryCode.domain.models.CountryCode;
import dev.connectme.connectme.countryCode.infrastructure.entities.CountryCodeEntity;
import dev.connectme.connectme.countryCode.infrastructure.repositories.JpaCountryCodeRepository;
import dev.connectme.connectme.phone.domain.models.Phone;
import dev.connectme.connectme.user.domain.models.User;
import dev.connectme.connectme.user.infrastructure.entities.UserEntity;
import dev.connectme.connectme.user.infrastructure.repositories.JpaUserRepository;
import dev.connectme.connectme.userStatus.domain.models.UserStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;
import java.util.List;

@DataJpaTest
public class UserEntityMapperTest {
    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Autowired
    private JpaCountryCodeRepository jpaCountryCodeRepository;

    @Test
    void shouldConvertFromDomainToEntity() {
        // Arrange
        UserStatus userStatus = UserStatus.builder()
                .statusName(UserStatus.StatusName.Active)
                .lastSeen(new Date())
                .build();
        CountryCodeEntity countryCode = CountryCodeEntity.builder()
                .code("+52")
                .flag("mx")
                .name("Mexico")
                .build();
        jpaCountryCodeRepository.save(countryCode);

        Phone phone = Phone.builder()
                .number("5569382066")
                .countryCode(countryCode.toDomainModel())
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
                .userStatus(userStatus)
                .phone(phone)
                .build();

        // Act
        UserEntity userToEntity = UserEntityMapper.fromDomain(user);
        UserEntity userSaved = jpaUserRepository.save(userToEntity);
        //Assert
        assertNotNull(userSaved);
        assertNotNull(userSaved.getId());
        assertEquals(userSaved.getCountry(), "Mexico");
        assertEquals(userSaved.getPassword(), "5569382066");
        assertEquals(userSaved.getEmail(), "test@test.com");
        assertEquals(userSaved.getGender(), User.Gender.MALE);
        assertFalse(userSaved.isVerify());
        assertEquals(userSaved.getProfilePicture(), "nose.png");
        assertEquals(userSaved.getLastName(), "sanchez");
        assertEquals(userSaved.getFirstName(), "jose");
        assertEquals(userSaved.getPhone().getNumber(), "5569382066");
        assertEquals(userSaved.getUserStatus().getStatusName(), UserStatus.StatusName.Active);
    }

    @Test
    void shouldConvertFromEntityToDomainWithOutTokens() {
        UserEntity user = UserEntity.builder()
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

        User userToDomain = user.toDomainModel();

        assertNotNull(userToDomain);
        assertEquals(userToDomain.getPassword(), "5569382066");
        assertEquals(userToDomain.getCountry(), "Mexico");
        assertEquals(userToDomain.getEmail(), "test@test.com");
        assertEquals(userToDomain.getGender(), User.Gender.MALE);
        assertFalse(userToDomain.isVerify());
        assertEquals(userToDomain.getProfilePicture(), "nose.png");
        assertEquals(userToDomain.getLastName(), "sanchez");
        assertEquals(userToDomain.getFirstName(), "jose");
    }
    @Test
    void shouldConvertFromEntityToDomainWithTokens() {
        TokenEntity token1 = TokenEntity.builder()
                .id(1L)
                .token("token")
                .tokenType(Token.TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        TokenEntity token2 = TokenEntity.builder()
                .id(1L)
                .token("token2")
                .tokenType(Token.TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        UserEntity user = UserEntity.builder()
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
                .tokens(List.of(token1, token2))
                .build();

        User userToDomain = user.toDomainModel();

        assertNotNull(userToDomain);
        assertEquals(userToDomain.getPassword(), "5569382066");
        assertEquals(userToDomain.getCountry(), "Mexico");
        assertEquals(userToDomain.getEmail(), "test@test.com");
        assertEquals(userToDomain.getGender(), User.Gender.MALE);
        assertFalse(userToDomain.isVerify());
        assertEquals(userToDomain.getProfilePicture(), "nose.png");
        assertEquals(userToDomain.getLastName(), "sanchez");
        assertEquals(userToDomain.getFirstName(), "jose");
        assertEquals(userToDomain.getTokens().size(), 2);
        assertEquals(userToDomain.getTokens().get(0).getToken(), "token");
        assertEquals(userToDomain.getTokens().get(1).getToken(), "token2");
    }

    @Test
    void shouldInstanceCorrectly() {
        assertDoesNotThrow(() -> {
            UserEntityMapper userEntityMapper = new UserEntityMapper();
        });
    }
}
