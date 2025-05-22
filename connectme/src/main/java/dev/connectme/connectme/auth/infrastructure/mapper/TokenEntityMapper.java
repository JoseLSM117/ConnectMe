package dev.connectme.connectme.auth.infrastructure.mapper;

import dev.connectme.connectme.auth.domain.models.Token;
import dev.connectme.connectme.auth.domain.models.TokenDTO;
import dev.connectme.connectme.auth.infrastructure.entities.TokenEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TokenEntityMapper {

    public static TokenEntity fromDomain(Token token) {
        TokenEntity tokenEntity = new TokenEntity(
                token.getId(),
                token.getToken(),
                token.isExpired(),
                token.isRevoked(),
                token.getTokenType(),
                null
        );
        return tokenEntity;
    }

    public static Token toDomain(TokenEntity tokenEntity) {
        return Token.builder()
                .id(tokenEntity.getId())
                .token(tokenEntity.getToken())
                .expired(tokenEntity.isExpired())
                .revoked(tokenEntity.isRevoked())
                .tokenType(tokenEntity.getTokenType())
                .build();
    }
}
