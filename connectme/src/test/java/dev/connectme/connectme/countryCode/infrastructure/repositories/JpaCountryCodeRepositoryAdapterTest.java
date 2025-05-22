package dev.connectme.connectme.countryCode.infrastructure.repositories;

import dev.connectme.connectme.countryCode.domain.models.CountryCode;
import dev.connectme.connectme.countryCode.infrastructure.entities.CountryCodeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JpaCountryCodeRepositoryAdapterTest {

    @Mock
    private JpaCountryCodeRepository jpaCountryCodeRepository;

    @InjectMocks
    private JpaCountryCodeRepositoryAdapter countryCodeRepositoryAdapter;

    @Captor
    private ArgumentCaptor<List<CountryCodeEntity>> listCaptor;

    @Test
    void shouldSaveCountryCodeCorrectly() {
        // Arrange
        CountryCode countryCode = CountryCode.builder()
                .name("Mexico")
                .flag("mx")
                .code("+52")
                .build();

        CountryCodeEntity savedEntity = CountryCodeEntity.builder()
                .id(1L)
                .name("Mexico")
                .flag("mx")
                .code("+52")
                .build();

        when(jpaCountryCodeRepository.save(any(CountryCodeEntity.class)))
                .thenReturn(savedEntity);
        // Act
        CountryCode result = countryCodeRepositoryAdapter.save(countryCode);

        //Assert
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(result.getName(), "Mexico");
        assertEquals(result.getFlag(), "mx");
        assertEquals(result.getCode(), "+52");
    }

    @Test
    void shouldFindCountryCodeByCode() {
        // Arrange
        String code = "+52";
        CountryCodeEntity savedEntity = CountryCodeEntity.builder()
                .id(1L)
                .name("Mexico")
                .flag("mx")
                .code(code)
                .build();

        when(jpaCountryCodeRepository.findByCode(code)).thenReturn(Optional.of(savedEntity));

        // Act
        Optional<CountryCode> foundCountryCodeOpt = countryCodeRepositoryAdapter.findByCode(code);

        // Assert
        assertTrue(foundCountryCodeOpt.isPresent());
        CountryCode foundCountryCode = foundCountryCodeOpt.get();
        assertEquals("Mexico", foundCountryCode.getName());
        assertEquals("mx", foundCountryCode.getFlag());
        assertEquals("+52", foundCountryCode.getCode());
        assertNotNull(foundCountryCode.getId());
    }

    @Test
    void shouldFindCountryCodeById() {
        // Arrange
        Long id = 1L;
        CountryCodeEntity savedEntity = CountryCodeEntity.builder()
                .id(1L)
                .name("Mexico")
                .flag("mx")
                .code("+52")
                .build();

        when(jpaCountryCodeRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(savedEntity));

        // Act
        Optional<CountryCode> foundCountryCode = countryCodeRepositoryAdapter.findById(id);

        // Assert
        assertTrue(foundCountryCode.isPresent());
        CountryCode countryCode = foundCountryCode.get();
        assertEquals(countryCode.getFlag(), "mx");
        assertEquals(countryCode.getCode(), "+52");
        assertEquals(countryCode.getName(), "Mexico");
    }

    @Test
    void shouldSaveAllCountryCodes() {
        // given
        CountryCode c1 = CountryCode.builder().name("Mexico").flag("mx").code("+52").build();
        CountryCode c2 = CountryCode.builder().name("US").flag("us").code("+1").build();
        List<CountryCode> domainList = List.of(c1, c2);

        // when
        countryCodeRepositoryAdapter.saveAll(domainList);

        // then
        verify(jpaCountryCodeRepository).saveAll(listCaptor.capture());
        List<CountryCodeEntity> captured = listCaptor.getValue();

        assertEquals(2, captured.size());
        assertEquals("Mexico", captured.get(0).getName());
        assertEquals("+52",   captured.get(0).getCode());
        assertEquals("mx",    captured.get(0).getFlag());
    }

    @Test
    void shouldReturnTrueIsEmptyMethod() {
        when(jpaCountryCodeRepository.count()).thenReturn(0L);
        assertTrue(countryCodeRepositoryAdapter.isEmpty());
    }

    @Test
    void shouldReturnFalseIsEmptyMethod() {
        when(jpaCountryCodeRepository.count()).thenReturn(1L);
        assertFalse(countryCodeRepositoryAdapter.isEmpty());
    }

    @Test
    void shouldFindAllCountryCode() {
        // Arrange
        CountryCodeEntity savedEntity1 = CountryCodeEntity.builder()
                .id(1L)
                .name("Mexico")
                .flag("mx")
                .code("+52")
                .build();
        CountryCodeEntity savedEntity2 = CountryCodeEntity.builder()
                .id(2L)
                .name("United States")
                .flag("us")
                .code("+1")
                .build();
        List<CountryCodeEntity> countryCodeEntities = List.of(savedEntity1, savedEntity2);

        when(jpaCountryCodeRepository.findAll()).thenReturn(countryCodeEntities);

        List<CountryCode> countryCodes = countryCodeRepositoryAdapter.findAll();

        assertNotNull(countryCodes);
        assertEquals(countryCodes.size(), 2);
        assertEquals(countryCodes.get(0), savedEntity1.toDomainModel());
        assertEquals(countryCodes.get(1), savedEntity2.toDomainModel());
    }
}
