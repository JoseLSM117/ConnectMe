package dev.connectme.connectme.countryCode.infrastructure.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.connectme.connectme.countryCode.application.usecases.StoreUseCase;
import dev.connectme.connectme.countryCode.infrastructure.dtos.in.ToStoreCountryCodeDto;
import dev.connectme.connectme.countryCode.infrastructure.dtos.out.StoredCountryCodeDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StoreCountryCodeController.class)
@AutoConfigureMockMvc(addFilters = false)
public class StoreCountryCodeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StoreUseCase storeUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldStoreCountryCodeCorrectlyWithStatus201() throws Exception {
        ToStoreCountryCodeDto toStoreCountryCodeDto = new ToStoreCountryCodeDto("Mexico", "+52", "mx");
        StoredCountryCodeDto storedCountryCodeDto = new StoredCountryCodeDto(1L, "Mexico", "+52", "mx");
        when(storeUseCase.execute(toStoreCountryCodeDto)).thenReturn(storedCountryCodeDto);

        String json = objectMapper.writeValueAsString(toStoreCountryCodeDto);


        mockMvc.perform(post("/api/country-codes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.message").value("Country Code Stored Correctly"))
                .andExpect(jsonPath("$.body.id").value(storedCountryCodeDto.getId()))
                .andExpect(jsonPath("$.body.code").value(storedCountryCodeDto.getCode()))
                .andExpect(jsonPath("$.body.name").value(storedCountryCodeDto.getName()))
                .andExpect(jsonPath("$.body.flag").value(storedCountryCodeDto.getFlag()));
        verify(storeUseCase).execute(any(ToStoreCountryCodeDto.class));
    }
}
