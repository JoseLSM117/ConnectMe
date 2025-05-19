package dev.connectme.connectme.countryCode.infrastructure.repositories;

import dev.connectme.connectme.countryCode.infrastructure.entities.CountryCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaCountryCodeRepository extends JpaRepository<CountryCodeEntity, Long> {
    Optional<CountryCodeEntity> findByCode(String code);
}
