package dev.connectme.connectme.auth.infrastructure.dtos.out;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RegisterUserDtoResponse {
    private String phoneNumber;
    private String countryCode;
    private String firstName;
    private String lastName;
    private String accessToken;
    private String refreshToken;
}
