package dev.connectme.connectme.auth.domain.ports;

import dev.connectme.connectme.auth.domain.models.Token;
import dev.connectme.connectme.user.domain.models.User;

public interface TokenRepositoryPort {
    Token save(Token token);
    void deleteAllTokensByUserId(Long userId);
}