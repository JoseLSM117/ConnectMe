package dev.connectme.connectme.userStatus.infrastructure.entities;

import dev.connectme.connectme.user.infrastructure.entities.UserEntity;
import dev.connectme.connectme.user.infrastructure.repositories.JpaUserRepository;
import dev.connectme.connectme.userStatus.domain.models.UserStatus;
import dev.connectme.connectme.userStatus.infrastructure.repositories.JpaUserStatusRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@RequiredArgsConstructor
@ExtendWith(MockitoExtension.class)
public class UserStatusEntityTest {

    @Autowired
    private JpaUserRepository userRepo;

    @Autowired
    private JpaUserStatusRepository statusRepo;

    @Test
    @Transactional
    void shouldBeFailWhenStatusNameIsNull() {
        assertThrows(Exception.class, () -> {
            UserStatusEntity userStatusEntity = UserStatusEntity.builder()
                    .id(1L)
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
}
