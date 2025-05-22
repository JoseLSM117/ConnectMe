package dev.connectme.connectme.user.infrastructure.repositories;

import dev.connectme.connectme.user.domain.models.User;
import dev.connectme.connectme.user.domain.ports.out.UserRepositoryPort;
import dev.connectme.connectme.user.infrastructure.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaUserRepositoryAdapter implements UserRepositoryPort {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        UserEntity userToSave = UserEntity.fromDomainModel(user);
        UserEntity userSaved = jpaUserRepository.save(userToSave);
        return userSaved.toDomainModel();
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById(id).map(UserEntity::toDomainModel);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email).map(UserEntity::toDomainModel);
    }
}
