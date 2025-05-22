package dev.connectme.connectme.auth.infrastructure.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.connectme.connectme.auth.application.usecases.RegisterUseCase;
import dev.connectme.connectme.auth.infrastructure.dtos.in.RegisterUserDTORequest;
import dev.connectme.connectme.auth.infrastructure.dtos.out.RegisterUserDtoResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private RegisterUseCase registerUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldRegisterUser() throws Exception {
        RegisterUserDTORequest registerUserDTORequest = RegisterUserDTORequest.builder()
                .firstName("Jose")
                .lastName("Sanchez")
                .countryCodeId(1L)
                .email("test@test.com")
                .phoneNumber("5569382066")
                .password("5569382066")
                .build();
        RegisterUserDtoResponse registerUserDtoResponse = RegisterUserDtoResponse.builder()
                .lastName("Sanchez")
                .firstName("Jose")
                .countryCode("+52")
                .refreshToken("refreshToken")
                .accessToken("accessToken")
                .phoneNumber("5569382066")
                .build();
        when(registerUseCase.execute(any(RegisterUserDTORequest.class))).thenReturn(registerUserDtoResponse);
        String json = objectMapper.writeValueAsString(registerUserDTORequest);

        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.message").value("User registered correctly"))
                .andExpect(jsonPath("$.body.phoneNumber").value("5569382066"))
                .andExpect(jsonPath("$.body.countryCode").value("+52"))
                .andExpect(jsonPath("$.body.firstName").value("Jose"))
                .andExpect(jsonPath("$.body.lastName").value("Sanchez"))
                .andExpect(jsonPath("$.body.accessToken").value("accessToken"))
                .andExpect(jsonPath("$.body.refreshToken").value("refreshToken"));
        verify(registerUseCase).execute(any(RegisterUserDTORequest.class));
    }
}
