package dev.connectme.connectme.countryCode.infrastructure.adapters;

import dev.connectme.connectme.countryCode.application.usecases.StoreUseCase;
import dev.connectme.connectme.countryCode.domain.exceptions.CountryCodeAlreadyExistException;
import dev.connectme.connectme.countryCode.infrastructure.controllers.StoreCountryCodeController;
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

@WebMvcTest(controllers = StoreCountryCodeController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CountryCodeExceptionHandlerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StoreUseCase storeUseCase;

    @Test
    void shouldReturnBadRequestWhenCountryCodeAlreadyExists() throws  Exception {
        when(storeUseCase.execute(any())).thenThrow(new CountryCodeAlreadyExistException());
        mockMvc.perform(post("/api/country-codes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\": \"+52\", \"name\": \"Mexico\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Country code already exists"))
                .andExpect(jsonPath("$.body").doesNotExist());
    }
}
