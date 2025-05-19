package dev.connectme.connectme.auth.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDTO {
    private Long id;
    private String token;
    private boolean expired;
    private boolean revoked;
    private Token.TokenType tokenType;
}
