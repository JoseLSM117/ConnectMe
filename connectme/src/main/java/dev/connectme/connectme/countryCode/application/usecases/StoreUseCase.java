package dev.connectme.connectme.countryCode.application.usecases;

import dev.connectme.connectme.countryCode.domain.exceptions.CountryCodeAlreadyExistException;
import dev.connectme.connectme.countryCode.domain.models.CountryCode;
import dev.connectme.connectme.countryCode.domain.ports.CountryCodeRepositoryPort;
import dev.connectme.connectme.countryCode.infrastructure.dtos.in.ToStoreCountryCodeDto;
import dev.connectme.connectme.countryCode.infrastructure.dtos.out.StoredCountryCodeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreUseCase {
    private final CountryCodeRepositoryPort countryCodeRepositoryPort;

    public StoredCountryCodeDto execute(ToStoreCountryCodeDto toStoreCountryCodeDto){
        Optional<CountryCode> foundCountryCode = countryCodeRepositoryPort.findByCode(toStoreCountryCodeDto.getCode());
        if(foundCountryCode.isPresent()){
            throw new CountryCodeAlreadyExistException();
        }
        CountryCode countryCode = CountryCode.builder()
                .name(toStoreCountryCodeDto.getName())
                .code(toStoreCountryCodeDto.getCode())
                .flag(toStoreCountryCodeDto.getFlag())
                .build();
        CountryCode savedCountryCode = countryCodeRepositoryPort.save(countryCode);
        return StoredCountryCodeDto.builder()
                .id(savedCountryCode.getId())
                .name(savedCountryCode.getName())
                .code(savedCountryCode.getCode())
                .flag(savedCountryCode.getFlag())
                .build();
    }
}
