package dev.connectme.connectme.auth.domain.models;

import dev.connectme.connectme.user.domain.models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
public class Token {

    public enum TokenType {
        BEARER
    }

    private Long id;

    @NotBlank(message = "Token is required")
    private String token;

    private boolean expired;

    private boolean revoked;

    @NotNull(message = "TokenType is required")
    private TokenType tokenType;

    @ToString.Exclude
    @NotNull(message = "User is required")
    private User user;

}
