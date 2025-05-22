package dev.connectme.connectme.countryCode.infrastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.connectme.connectme.countryCode.application.usecases.FindAllUseCase;
import dev.connectme.connectme.countryCode.domain.models.CountryCode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = FindAllCountryCodesController.class)
@AutoConfigureMockMvc(addFilters = false)
public class FindAllCountryCodeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private FindAllUseCase findAllUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldFoundAllCountryCodes() throws Exception {
        CountryCode countryCode = CountryCode.builder()
                .code("+52")
                .flag("mx")
                .name("Mexico")
                .id(1L)
                .build();
        CountryCode countryCode2 = CountryCode.builder()
                .code("+1")
                .flag("en")
                .name("United States")
                .id(2L)
                .build();
        List<CountryCode> storedCountryCodes = List.of(countryCode, countryCode2);

        when(findAllUseCase.execute()).thenReturn(storedCountryCodes);

        String expectedJson = objectMapper.writeValueAsString(
                Map.of(
                        "status", 200,
                        "message", "Success",
                        "body", storedCountryCodes
                )
        );

        mockMvc.perform(get("/api/country-codes"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));

    }
}
