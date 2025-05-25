package dev.connectme.connectme.auth.infrastructure.controllers;

import dev.connectme.connectme.auth.application.usecases.LoginUseCase;
import dev.connectme.connectme.auth.application.usecases.RegisterUseCase;
import dev.connectme.connectme.auth.infrastructure.dtos.in.LoginUserDtoRequest;
import dev.connectme.connectme.auth.infrastructure.dtos.in.RegisterUserDTORequest;
import dev.connectme.connectme.auth.infrastructure.dtos.out.RegisterUserDtoResponse;
import dev.connectme.connectme.shared.infrastructure.responses.ApiResponse;
import dev.connectme.connectme.user.domain.models.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private final RegisterUseCase registerUseCase;

    @Autowired
    private final LoginUseCase loginUseCase;

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

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<User>> login(@Valid @RequestBody LoginUserDtoRequest loginUserDtoRequest) {
        User loggedUser = loginUseCase.execute(loginUserDtoRequest);
        ApiResponse<User> response = new ApiResponse<>(
                200,
                "User registered correctly",
                loggedUser
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
