package dev.connectme.connectme.phone.infrastructure.repositories;

import dev.connectme.connectme.phone.infrastructure.entities.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaPhoneRepository extends JpaRepository<PhoneEntity, Long> {
    Optional<PhoneEntity> findByNumber(String number);
}
