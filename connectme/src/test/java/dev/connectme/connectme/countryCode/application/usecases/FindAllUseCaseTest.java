package dev.connectme.connectme.countryCode.application.usecases;

import dev.connectme.connectme.countryCode.domain.models.CountryCode;
import dev.connectme.connectme.countryCode.domain.ports.CountryCodeRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FindAllUseCaseTest {
    @Mock
    private CountryCodeRepositoryPort countryCodeRepositoryPort;

    @InjectMocks
    private FindAllUseCase findAllUseCase;

    @Test
    void shouldFindAllCountryCode() {
        CountryCode c1 = CountryCode.builder().name("Mexico").flag("mx").code("+52").build();
        CountryCode c2 = CountryCode.builder().name("US").flag("us").code("+1").build();
        List<CountryCode> countryCodeList = List.of(c1, c2);

        when(countryCodeRepositoryPort.findAll()).thenReturn(countryCodeList);

        List<CountryCode> countryCodes = findAllUseCase.execute();

        assertEquals(countryCodes.size(), 2);
        assertEquals(countryCodes.get(0), c1);
        assertEquals(countryCodes.get(1), c2);
    }
}
