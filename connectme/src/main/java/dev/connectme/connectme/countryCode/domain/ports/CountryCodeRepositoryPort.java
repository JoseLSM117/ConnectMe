package dev.connectme.connectme.countryCode.domain.ports;

import dev.connectme.connectme.countryCode.domain.models.CountryCode;

import java.util.List;
import java.util.Optional;

public interface CountryCodeRepositoryPort {
    CountryCode save(CountryCode countryCode);
    Optional<CountryCode> findByCode(String code);
    Optional<CountryCode> findById(Long id);
    void saveAll(List<CountryCode> countryCodes);
    boolean isEmpty();
    List<CountryCode> findAll();
}
