package dev.connectme.connectme.user.domain.ports.out;

import dev.connectme.connectme.user.domain.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}
