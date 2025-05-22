package dev.connectme.connectme.countryCode.application.usecases;

import dev.connectme.connectme.countryCode.domain.exceptions.CountryCodeAlreadyExistException;
import dev.connectme.connectme.countryCode.domain.models.CountryCode;
import dev.connectme.connectme.countryCode.domain.ports.CountryCodeRepositoryPort;
import dev.connectme.connectme.countryCode.infrastructure.dtos.in.ToStoreCountryCodeDto;
import dev.connectme.connectme.countryCode.infrastructure.dtos.out.StoredCountryCodeDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class StoreUseCaseTest {
    @Mock
    private CountryCodeRepositoryPort countryCodeRepositoryPort;

    @InjectMocks
    private StoreUseCase storeUseCase;

    @Test
    void shouldThrowCountryCodeAlreadyExistException() {
        CountryCode foundCountryCode = CountryCode.builder()
                .name("Mexico")
                .flag("mx")
                .code("+52")
                .build();
        when(countryCodeRepositoryPort.findByCode(any(String.class))).thenReturn(Optional.ofNullable(foundCountryCode));
        ToStoreCountryCodeDto toStoreCountryCodeDto = ToStoreCountryCodeDto.builder()
                .code("+52")
                .flag("mx")
                .name("Mexico")
                .build();
        assertThrows(CountryCodeAlreadyExistException.class, () -> {
            storeUseCase.execute(toStoreCountryCodeDto);
        });
    }

    @Test
    void shouldSaveCountryCodeCorrectly() {
        CountryCode countryCodeSaved = CountryCode.builder()
                .id(1L)
                .name("Mexico")
                .flag("mx")
                .code("+52")
                .build();
        ToStoreCountryCodeDto toStoreCountryCodeDto = ToStoreCountryCodeDto.builder()
                .code("+52")
                .flag("mx")
                .name("Mexico")
                .build();

        when(countryCodeRepositoryPort.findByCode(any(String.class))).thenReturn(Optional.empty());
        when(countryCodeRepositoryPort.save(any(CountryCode.class))).thenReturn(countryCodeSaved);
        StoredCountryCodeDto storedCountryCodeDto = storeUseCase.execute(toStoreCountryCodeDto);

        assertNotNull(storedCountryCodeDto);
        assertEquals(countryCodeSaved.getId(), storedCountryCodeDto.getId());
        assertEquals(toStoreCountryCodeDto.getCode(), storedCountryCodeDto.getCode());
        assertEquals(toStoreCountryCodeDto.getName(), storedCountryCodeDto.getName());
        assertEquals(toStoreCountryCodeDto.getFlag(), storedCountryCodeDto.getFlag());
    }
}
