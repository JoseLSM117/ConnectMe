package dev.connectme.connectme.auth.infrastructure.dtos.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginUserDtoRequest {
    @NotBlank(message = "Phone number is required")
    @Size(min = 4, max = 15, message = "Phone number must be between 4 and 15 digits")
    @Pattern(regexp = "[0-9]+", message = "Phone number must contain only digits (no spaces or symbols)")
    private String phoneNumber;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String password;
}
