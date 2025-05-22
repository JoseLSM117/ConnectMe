package dev.connectme.connectme.user.infrastructure.repositories;

import dev.connectme.connectme.phone.domain.models.Phone;
import dev.connectme.connectme.phone.infrastructure.entities.PhoneEntity;
import dev.connectme.connectme.user.domain.models.User;
import dev.connectme.connectme.user.infrastructure.entities.UserEntity;
import dev.connectme.connectme.userStatus.domain.models.UserStatus;
import dev.connectme.connectme.userStatus.infrastructure.entities.UserStatusEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class JpaRepositoryAdapterTest {
    @Mock
    private JpaUserRepository jpaUserRepository;

    @InjectMocks
    private JpaUserRepositoryAdapter jpaUserRepositoryAdapter;

    private UserEntity userSaved;
    private User userToSave;

    @BeforeEach
    void setUp() {
        UserStatusEntity userStatus = UserStatusEntity.builder()
                .statusName(UserStatus.StatusName.Active)
                .lastSeen(new Date())
                .build();
        PhoneEntity phone = PhoneEntity.builder()
                .number("5569382066")
                .build();
        userSaved = UserEntity.builder()
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
        userToSave = User.builder()
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
                .userStatus(userStatus.toDomain())
                .phone(phone.toDomain())
                .build();
    }

    @Test
    void shouldSaveANewUser() {

        when(jpaUserRepository.save(any(UserEntity.class))).thenReturn(userSaved);
        User userStored = jpaUserRepositoryAdapter.save(userToSave);

        assertNotNull(userStored);
        assertEquals(userStored.getPassword(), "5569382066");
        assertEquals(userStored.getCountry(), "Mexico");
        assertEquals(userStored.getEmail(), "test@test.com");
        assertEquals(userStored.getGender(), User.Gender.MALE);
        assertFalse(userStored.isVerify());
        assertEquals(userStored.getFirstName(), "jose");
        assertEquals(userStored.getLastName(), "sanchez");
        assertEquals(userStored.getUserStatus().getStatusName(), UserStatus.StatusName.Active);
        assertEquals(userStored.getPhone().getNumber(), "5569382066");

        verify(jpaUserRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void shouldFindUserById() {
        userSaved.setId(1L);
        when(jpaUserRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(userSaved));
        User userFound = jpaUserRepositoryAdapter.findById(1L).orElseThrow();

        assertEquals(userFound.getId(), 1L);
        assertEquals(userFound.getPassword(), "5569382066");
        assertEquals(userFound.getCountry(), "Mexico");
        assertEquals(userFound.getEmail(), "test@test.com");
        assertEquals(userFound.getGender(), User.Gender.MALE);
        assertFalse(userFound.isVerify());
        assertEquals(userFound.getFirstName(), "jose");
        assertEquals(userFound.getLastName(), "sanchez");
        assertEquals(userFound.getUserStatus().getStatusName(), UserStatus.StatusName.Active);
        assertEquals(userFound.getPhone().getNumber(), "5569382066");
    }

    @Test
    void shouldFindUserByEmail() {
        when(jpaUserRepository.findByEmail(any(String.class))).thenReturn(Optional.ofNullable(userSaved));
        User userFound = jpaUserRepositoryAdapter.findByEmail("test@test.com").orElseThrow();

        assertEquals(userFound.getPassword(), "5569382066");
        assertEquals(userFound.getCountry(), "Mexico");
        assertEquals(userFound.getEmail(), "test@test.com");
        assertEquals(userFound.getGender(), User.Gender.MALE);
        assertFalse(userFound.isVerify());
        assertEquals(userFound.getFirstName(), "jose");
        assertEquals(userFound.getLastName(), "sanchez");
        assertEquals(userFound.getUserStatus().getStatusName(), UserStatus.StatusName.Active);
        assertEquals(userFound.getPhone().getNumber(), "5569382066");
    }
}
