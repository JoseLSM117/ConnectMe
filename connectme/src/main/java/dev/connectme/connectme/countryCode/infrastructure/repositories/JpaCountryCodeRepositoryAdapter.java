package dev.connectme.connectme.countryCode.infrastructure.repositories;

import dev.connectme.connectme.countryCode.domain.models.CountryCode;
import dev.connectme.connectme.countryCode.domain.ports.CountryCodeRepositoryPort;
import dev.connectme.connectme.countryCode.infrastructure.entities.CountryCodeEntity;
import dev.connectme.connectme.countryCode.infrastructure.mapper.CountryCodeEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class JpaCountryCodeRepositoryAdapter implements CountryCodeRepositoryPort {
    private final JpaCountryCodeRepository jpaCountryCodeRepository;

    @Override
    public CountryCode save(CountryCode countryCode) {
        CountryCodeEntity countryCodeEntity = CountryCodeEntity.fromDomainModel(countryCode);
        CountryCodeEntity countryCodeEntitySaved = jpaCountryCodeRepository.save(countryCodeEntity);
        return countryCodeEntitySaved.toDomainModel();
    }

    @Override
    public Optional<CountryCode> findByCode(String code) {
        Optional<CountryCodeEntity> countryCodeEntityFound = jpaCountryCodeRepository.findByCode(code);
        return countryCodeEntityFound.map(CountryCodeEntity::toDomainModel);
    }

    @Override
    public Optional<CountryCode> findById(Long id) {
        Optional<CountryCodeEntity> countryCodeEntityFound = jpaCountryCodeRepository.findById(id);
        return countryCodeEntityFound.map(CountryCodeEntity::toDomainModel);
    }

    @Override
    public void saveAll(List<CountryCode> countryCodes) {
        List<CountryCodeEntity> countryCodeEntities = countryCodes.stream()
                .map(CountryCodeEntityMapper::fromDomain)
                .toList();
        jpaCountryCodeRepository.saveAll(countryCodeEntities);
    }

    @Override
    public boolean isEmpty() {
        return jpaCountryCodeRepository.count() == 0;
    }

    @Override
    public List<CountryCode> findAll() {
        return jpaCountryCodeRepository.findAll().stream().map(CountryCodeEntity::toDomainModel).collect(Collectors.toList());
    }
}
