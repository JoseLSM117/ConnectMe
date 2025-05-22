package dev.connectme.connectme.countryCode.domain.exceptions;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class CountryCodeAlreadyExistExceptionTest {
    @Test
    void shouldBeThrowAnExceptionWithMessage(){
        String message = "Country code already exists";
        CountryCodeAlreadyExistException exception = new CountryCodeAlreadyExistException(message);
        assertEquals(message, exception.getMessage());
    }
}
