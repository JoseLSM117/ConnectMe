package dev.connectme.connectme.countryCode.infrastructure.dtos.out;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class StoredCountryCodeDtoTest {
    @Test
    void shouldInstanceCorrectlyTheInstance() {
        StoredCountryCodeDto storedCountryCodeDto = new StoredCountryCodeDto(1L, "Mexico", "+52", "mx");
        assertNotNull(storedCountryCodeDto);
        assertEquals(1L, storedCountryCodeDto.getId());
        assertEquals("Mexico", storedCountryCodeDto.getName());
        assertEquals("+52", storedCountryCodeDto.getCode());
        assertEquals("mx", storedCountryCodeDto.getFlag());
    }
}
