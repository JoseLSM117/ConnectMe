package dev.connectme.connectme.userStatus.infrastructure.mapper;

import dev.connectme.connectme.userStatus.domain.models.UserStatus;
import dev.connectme.connectme.userStatus.infrastructure.entities.UserStatusEntity;

public class UserStatusEntityMapper {
    public static UserStatusEntity fromDomain(UserStatus userStatus){
        return new UserStatusEntity(
                userStatus.getId(),
                userStatus.getStatusName(),
                userStatus.getLastSeen(),
                null
        );
    }

    public static UserStatus toDomain(UserStatusEntity userStatusEntity){
        return UserStatus.builder()
                .id(userStatusEntity.getId())
                .statusName(userStatusEntity.getStatusName())
                .lastSeen(userStatusEntity.getLastSeen())
                .build();
    }
}
