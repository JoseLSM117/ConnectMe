package dev.connectme.connectme.userStatus.infrastructure.repositories;

import dev.connectme.connectme.userStatus.infrastructure.entities.UserStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserStatusRepository extends JpaRepository<UserStatusEntity, Long> {
}
