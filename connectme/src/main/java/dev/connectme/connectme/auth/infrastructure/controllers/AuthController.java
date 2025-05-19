package dev.connectme.connectme.auth.infrastructure.controllers;

import dev.connectme.connectme.auth.application.usecases.RegisterUseCase;
import dev.connectme.connectme.auth.infrastructure.dtos.in.RegisterUserDTORequest;
import dev.connectme.connectme.auth.infrastructure.dtos.out.RegisterUserDtoResponse;
import dev.connectme.connectme.shared.infrastructure.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final RegisterUseCase registerUseCase;
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterUserDtoResponse>> register(@Valid @RequestBody RegisterUserDTORequest registerUserDTO) {
        RegisterUserDtoResponse registeredUser = registerUseCase.execute(registerUserDTO);
        ApiResponse<RegisterUserDtoResponse> response = new ApiResponse<>(
                201,
                "User registered correctly",
                registeredUser
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
