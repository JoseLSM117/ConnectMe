package dev.connectme.connectme.auth.infrastructure.repositories;

import dev.connectme.connectme.auth.infrastructure.entities.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTokenRepository extends JpaRepository<TokenEntity, Long> {
}
