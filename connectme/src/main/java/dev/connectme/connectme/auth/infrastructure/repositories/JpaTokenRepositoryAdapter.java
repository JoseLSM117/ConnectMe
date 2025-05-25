package dev.connectme.connectme.auth.infrastructure.repositories;

import dev.connectme.connectme.auth.domain.models.Token;
import dev.connectme.connectme.auth.domain.ports.TokenRepositoryPort;
import dev.connectme.connectme.auth.infrastructure.entities.TokenEntity;
import dev.connectme.connectme.user.domain.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaTokenRepositoryAdapter implements TokenRepositoryPort {
    private final JpaTokenRepository jpaTokenRepository;
    @Override
    public Token save(Token token) {
        TokenEntity tokenEntity = TokenEntity.fromDomainModel(token);
        TokenEntity tokenEntitySaved = jpaTokenRepository.save(tokenEntity);
        return tokenEntitySaved.toDomainModel();
    }

    @Override
    public void deleteAllTokensByUserId(Long userId) {
        jpaTokenRepository.deleteAllByUserId(userId);
    }
}
