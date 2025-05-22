package dev.connectme.connectme.countryCode.infrastructure.mapper;

import dev.connectme.connectme.countryCode.domain.models.CountryCode;
import dev.connectme.connectme.countryCode.infrastructure.entities.CountryCodeEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CountryCodeEntityMapperTest {

    @Test
    void shouldInstanceEntityFromDomain() {
        CountryCode countryCode = CountryCode.builder()
                .code("+52")
                .flag("mx")
                .name("Mexico")
                .build();
        CountryCodeEntity countryCodeEntity = CountryCodeEntity.fromDomainModel(countryCode);

        assertNotNull(countryCodeEntity);
        assertEquals(countryCodeEntity.getCode(), "+52");
        assertEquals(countryCodeEntity.getFlag(), "mx");
        assertEquals(countryCodeEntity.getName(), "Mexico");
    }

    @Test
    void shouldConvertFromEntityToDomainModel() {
        CountryCodeEntity countryCodeEntity = CountryCodeEntity.builder()
                .name("Mexico")
                .flag("mx")
                .code("+52")
                .build();
        CountryCode countryCode = countryCodeEntity.toDomainModel();

        assertNotNull(countryCode);
        assertEquals(countryCode.getCode(), "+52");
        assertEquals(countryCode.getFlag(), "mx");
        assertEquals(countryCode.getName(), "Mexico");
    }

    @Test
    void shouldInstanceCorrectly() {
        CountryCodeEntityMapper countryCodeEntityMapper = new CountryCodeEntityMapper();
    }
}
