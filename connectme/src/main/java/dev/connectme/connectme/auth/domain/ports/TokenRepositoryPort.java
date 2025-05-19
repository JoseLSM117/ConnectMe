package dev.connectme.connectme.auth.domain.ports;

import dev.connectme.connectme.auth.domain.models.Token;

public interface TokenRepositoryPort {
    Token save(Token token);
}
