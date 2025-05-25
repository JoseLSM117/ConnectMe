package dev.connectme.connectme.auth.infrastructure.repositories;

import dev.connectme.connectme.auth.infrastructure.entities.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface JpaTokenRepository extends JpaRepository<TokenEntity, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM TokenEntity t WHERE t.user.id = :userId")
    void deleteAllByUserId(@Param("userId") Long userId);
}
