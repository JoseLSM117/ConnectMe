package dev.connectme.connectme.user.infrastructure.repositories;

import dev.connectme.connectme.user.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
