package dev.connectme.connectme.userStatus.infrastructure.entities;

import dev.connectme.connectme.user.domain.models.User;
import dev.connectme.connectme.user.infrastructure.entities.UserEntity;
import dev.connectme.connectme.user.infrastructure.repositories.JpaUserRepository;
import dev.connectme.connectme.userStatus.domain.models.UserStatus;
import dev.connectme.connectme.userStatus.infrastructure.repositories.JpaUserStatusRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@RequiredArgsConstructor
public class UserStatusEntityTest {

    @Autowired
    private JpaUserRepository userRepo;

    @Autowired
    private JpaUserStatusRepository statusRepo;

    @Test
    void shouldInstanceCorrectly() {
        Date lastSeen = new Date();

        UserEntity user = UserEntity.builder()
                .email("test@test.com")
                .country("xd")
                .firstName("test")
                .lastName("Test2")
                .password("alv")
                .build();

        UserEntity userSaved = userRepo.save(user);

        UserStatusEntity userStatusEntity = UserStatusEntity.builder()
                .lastSeen(lastSeen)
                .statusName(UserStatus.StatusName.Active)
                .user(userSaved)
                .build();
        UserStatusEntity userStatusSaved = statusRepo.save(userStatusEntity);

        assertNotNull(userStatusSaved);
        assertEquals(userStatusSaved.getStatusName(), UserStatus.StatusName.Active);
        assertEquals(userStatusSaved.getLastSeen(), lastSeen);
    }

    @Test
    void shouldBeFailWhenStatusNameIsNull() {
        assertThrows(Exception.class, () -> {
            UserEntity user = UserEntity.builder()
                    .email("test@test.com")
                    .country("xd")
                    .firstName("test")
                    .lastName("Test2")
                    .password("alv")
                    .build();

            UserEntity userSaved = userRepo.save(user);
            UserStatusEntity userStatusEntity = UserStatusEntity.builder()
                    .lastSeen(new Date())
                    .user(userSaved)
                    .build();
            statusRepo.save(userStatusEntity);
        });
    }

    @Test
    void shouldThrowExceptionIfLastSeenIsNull() {
        assertThrows(Exception.class, () -> {
            UserEntity user = UserEntity.builder()
                    .email("test@test.com")
                    .country("xd")
                    .firstName("test")
                    .lastName("Test2")
                    .password("alv")
                    .build();

            UserEntity userSaved = userRepo.save(user);
            UserStatusEntity userStatusEntity = UserStatusEntity.builder()
                    .statusName(UserStatus.StatusName.Active)
                    .user(userSaved)
                    .build();
            statusRepo.save(userStatusEntity);
        });
    }

    @Test
    void shouldThrowExceptionIfUserRelationIsNull() {
        assertThrows(Exception.class, () -> {
            UserStatusEntity userStatusEntity = UserStatusEntity.builder()
                    .statusName(UserStatus.StatusName.Active)
                    .lastSeen(new Date())
                    .build();
            statusRepo.save(userStatusEntity);
        });
    }

    @Test
    @Transactional
    void shouldMaintainBidirectionalRelationshipWithUser() {
        UserEntity user = UserEntity.builder()
                .email("test@test.com")
                .country("xd")
                .firstName("test")
                .lastName("Test2")
                .password("alv")
                .build();
        UserStatusEntity status = UserStatusEntity.builder()
                .statusName(UserStatus.StatusName.Active)
                .lastSeen(new Date())
                .build();
        status.setUser(user);
        user.setUserStatus(status);

        userRepo.save(user);
        statusRepo.save(status);

        UserEntity found = userRepo.findById(user.getId()).orElseThrow();
        assertEquals(status.getId(), found.getUserStatus().getId());
    }

    @Test
    void shouldConvertToDomain() {
        UserEntity user = UserEntity.builder()
                .firstName("jose")
                .lastName("sanchez")
                .password("5569382066")
                .isVerify(false)
                .build();
        userRepo.save(user);
        UserStatusEntity userStatus = UserStatusEntity.builder()
                .statusName(UserStatus.StatusName.Active)
                .lastSeen(new Date())
                .user(user)
                .build();
        UserStatusEntity userStatusSaved = statusRepo.save(userStatus);
        UserStatus userStatusConverted = userStatusSaved.toDomain();

        assertNotNull(userStatusConverted);
        assertEquals(userStatusConverted.getStatusName(), UserStatus.StatusName.Active);
    }

    @Test
    void shouldConvertToEntity(){
        UserStatus userStatus = UserStatus.builder()
                .lastSeen(new Date())
                .statusName(UserStatus.StatusName.Active)
                .build();
        UserStatusEntity userStatusEntity = UserStatusEntity.fromDomainModel(userStatus);

        assertNotNull(userStatusEntity);
        assertEquals(userStatusEntity.getStatusName(), UserStatus.StatusName.Active);
    }
}
