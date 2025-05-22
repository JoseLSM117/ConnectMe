package dev.connectme.connectme.user.infrastructure.entities;

import dev.connectme.connectme.auth.domain.models.Token;
import dev.connectme.connectme.auth.infrastructure.entities.TokenEntity;
import dev.connectme.connectme.auth.infrastructure.repositories.JpaTokenRepository;
import dev.connectme.connectme.countryCode.infrastructure.entities.CountryCodeEntity;
import dev.connectme.connectme.countryCode.infrastructure.repositories.JpaCountryCodeRepository;
import dev.connectme.connectme.phone.infrastructure.entities.PhoneEntity;
import dev.connectme.connectme.phone.infrastructure.repositories.JpaPhoneRepository;
import dev.connectme.connectme.user.domain.models.User;
import dev.connectme.connectme.user.infrastructure.repositories.JpaUserRepository;
import dev.connectme.connectme.userStatus.domain.models.UserStatus;
import dev.connectme.connectme.userStatus.infrastructure.entities.UserStatusEntity;
import dev.connectme.connectme.userStatus.infrastructure.repositories.JpaUserStatusRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@DataJpaTest
public class UserEntityTest {
    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Autowired
    private JpaUserStatusRepository jpaUserStatusRepository;

    @Autowired
    private JpaPhoneRepository jpaPhoneRepository;

    @Autowired
    private JpaTokenRepository jpaTokenRepository;

    @Autowired
    private JpaCountryCodeRepository jpaCountryCodeRepository;


    @Test
    void shouldInstanceCorrectly(){
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
        UserEntity userSaved = jpaUserRepository.save(user);

        assertNotNull(userSaved);
        assertEquals(userSaved.getPassword(), "5569382066");
        assertEquals(userSaved.getCountry(), "Mexico");
        assertEquals(userSaved.getEmail(), "test@test.com");
        assertEquals(userSaved.getGender(), User.Gender.MALE);
        assertFalse(userSaved.isVerify());
        assertEquals(userSaved.getProfilePicture(), "nose.png");
        assertEquals(userSaved.getLastName(), "sanchez");
        assertEquals(userSaved.getFirstName(), "jose");
    }

    @Test
    void shouldThrowExceptionIfFirstNameIsNull() {
        assertThrows(Exception.class, () -> {
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
                    .build();
            jpaUserRepository.save(user);
        });
    }

    @Test
    void shouldThrowExceptionIfLastNameIsNull() {
        assertThrows(Exception.class, () -> {
            UserEntity user = UserEntity.builder()
                    .password("5569382066")
                    .country("Mexico")
                    .email("test@test.com")
                    .gender(User.Gender.MALE)
                    .isVerify(false)
                    .createAt(new Date())
                    .updateAt(new Date())
                    .profilePicture("nose.png")
                    .firstName("jose")
                    .build();
            jpaUserRepository.save(user);
        });
    }

    @Test
    void shouldThrowExceptionIfPasswordIsNull() {
        assertThrows(Exception.class, () -> {
            UserEntity user = UserEntity.builder()
                    .country("Mexico")
                    .email("test@test.com")
                    .gender(User.Gender.MALE)
                    .isVerify(false)
                    .createAt(new Date())
                    .updateAt(new Date())
                    .profilePicture("nose.png")
                    .firstName("jose")
                    .lastName("sanchez")
                    .build();
            jpaUserRepository.save(user);
        });
    }

    @Test
    @Transactional
    void shouldMaintainBidirectionalRelationShip() {
        CountryCodeEntity countryCode = CountryCodeEntity.builder()
                .code("+52")
                .flag("mx")
                .name("Mexico")
                .build();
        jpaCountryCodeRepository.save(countryCode);

        TokenEntity token = TokenEntity.builder()
                .token("Token")
                .expired(false)
                .tokenType(Token.TokenType.BEARER)
                .revoked(false)
                .build();

        UserStatusEntity userStatusEntity = UserStatusEntity.builder()
                .lastSeen(new Date())
                .statusName(UserStatus.StatusName.Active)
                .build();
        PhoneEntity phone = PhoneEntity.builder()
                .number("5569382066")
                .countryCode(countryCode)
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
                .build();

        // Set up relationships
        user.setUserStatus(userStatusEntity);
        userStatusEntity.setUser(user);

        user.setTokens(List.of(token));
        token.setUser(user);

        user.setPhone(phone);
        phone.setUser(user);

        // Save the user (with cascade, saves everything)
        UserEntity savedUser = jpaUserRepository.save(user);

        UserStatusEntity userStatusFound = jpaUserStatusRepository.findById(savedUser.getUserStatus().getId()).orElseThrow();
        TokenEntity tokenEntityFound = jpaTokenRepository.findById(savedUser.getTokens().get(0).getId()).orElseThrow();
        PhoneEntity phoneEntity = jpaPhoneRepository.findById(savedUser.getPhone().getId()).orElseThrow();
        assertNotNull(userStatusFound);
        assertEquals(userStatusFound.getId(), savedUser.getUserStatus().getId());
        assertEquals(tokenEntityFound.getId(), savedUser.getTokens().get(0).getId());
        assertEquals(phoneEntity.getId(), savedUser.getPhone().getId());
    }

    @Test
    void shouldAddTokenWithTheMethod() {
        TokenEntity token1 = TokenEntity.builder()
                .revoked(false)
                .tokenType(Token.TokenType.BEARER)
                .token("token1")
                .expired(false)
                .build();
        TokenEntity token2 = TokenEntity.builder()
                .revoked(false)
                .tokenType(Token.TokenType.BEARER)
                .token("token2")
                .expired(false)
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
                .tokens(new ArrayList<>())
                .build();

        token1.setUser(user);
        user.addToken(token1);
        token2.setUser(user);
        user.addToken(token2);
        UserEntity userSaved = jpaUserRepository.save(user);

        assertNotNull(userSaved);
        assertEquals(userSaved.getTokens().size(), 2);
        assertEquals(userSaved.getTokens().get(0).getToken(), "token1");
        assertEquals(userSaved.getTokens().get(1).getToken(), "token2");
    }
}
