package dev.connectme.connectme.countryCode.application.init;

import dev.connectme.connectme.countryCode.domain.models.CountryCode;
import dev.connectme.connectme.countryCode.domain.ports.CountryCodeRepositoryPort;
import dev.connectme.connectme.countryCode.infrastructure.entities.CountryCodeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CountryCodeInitializerTest {
    @Mock
    private CountryCodeRepositoryPort countryCodeRepositoryPort;

    @InjectMocks
    private CountryCodeInitializer initializer;

    @Captor
    private ArgumentCaptor<List<CountryCode>> listCaptor;

    @Test
    void shouldSaveDefaultCountriesWhenRepositoryIsEmpty() throws Exception {
        when(countryCodeRepositoryPort.isEmpty()).thenReturn(true);

        initializer.run();

        verify(countryCodeRepositoryPort).saveAll(listCaptor.capture());

        List<CountryCode> savedCountries = listCaptor.getValue();

        assertNotNull(savedCountries);
        assertEquals(29, savedCountries.size());
        assertTrue(savedCountries.stream().anyMatch(c -> c.getName().equals("Mexico")));
        assertTrue(savedCountries.stream().anyMatch(c -> c.getCode().equals("+52")));
    }

    @Test
    void shouldNotSaveAnythingIfRepositoryIsNotEmpty() throws Exception {
        when(countryCodeRepositoryPort.isEmpty()).thenReturn(false);

        initializer.run(); // Ejecuta el método

        verify(countryCodeRepositoryPort, never()).saveAll(any());
    }
}
