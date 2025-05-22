package dev.connectme.connectme.userStatus.infrastructure.mapper;

import dev.connectme.connectme.userStatus.domain.models.UserStatus;
import dev.connectme.connectme.userStatus.infrastructure.entities.UserStatusEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class UserStatusEntityMapperTest {
    private final Date testTime = new Date();

    @Test
    void fromDomain_ShouldConvertUserStatusToEntityCorrectly(){
        // Arrange
        UserStatus domain = UserStatus.builder()
                .id(1L)
                .statusName(UserStatus.StatusName.Active)
                .lastSeen(testTime)
                .build();
        // Act
        UserStatusEntity entity = UserStatusEntityMapper.fromDomain(domain);

        // Assert
        assertNotNull(entity);
        assertEquals(domain.getId(), entity.getId());
        assertEquals(domain.getStatusName(), entity.getStatusName());
        assertEquals(domain.getLastSeen(), entity.getLastSeen());
        assertNull(entity.getUser());
    }

    @Test
    void toDomain_ShouldConvertEntityToUserStatusCorrectly() {
        // Arrange
        UserStatusEntity entity = new UserStatusEntity(
                1L,
                UserStatus.StatusName.Active,
                testTime,
                null
        );

        // Act
        UserStatus domain = UserStatusEntityMapper.toDomain(entity);

        // Assert
        assertNotNull(domain);
        assertEquals(entity.getId(), domain.getId());
        assertEquals(entity.getStatusName(), domain.getStatusName());
        assertEquals(entity.getLastSeen(), domain.getLastSeen());
    }

    @Test
    void bidirectionalConversion_ShouldMaintainDataIntegrity() {
        // Arrange
        UserStatus originalDomain = UserStatus.builder()
                .id(2L)
                .statusName(UserStatus.StatusName.Active)
                .lastSeen(testTime)
                .build();

        // Act
        UserStatusEntity entity = UserStatusEntityMapper.fromDomain(originalDomain);
        UserStatus convertedDomain = UserStatusEntityMapper.toDomain(entity);

        // Assert
        assertEquals(originalDomain.getId(), convertedDomain.getId());
        assertEquals(originalDomain.getStatusName(), convertedDomain.getStatusName());
        assertEquals(originalDomain.getLastSeen(), convertedDomain.getLastSeen());
    }
    @Test
    void shouldInstanceCorrectly() {
        assertDoesNotThrow(() -> {
            UserStatusEntityMapper userStatusEntityMapper = new UserStatusEntityMapper();
        });
    }
}
