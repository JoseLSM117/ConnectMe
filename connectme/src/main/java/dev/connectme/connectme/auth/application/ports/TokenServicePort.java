package dev.connectme.connectme.auth.application.ports;

import dev.connectme.connectme.auth.domain.models.Token;
import dev.connectme.connectme.user.domain.models.User;

public interface TokenServicePort {
    Token generateAccessToken(User user);
    Token generateRefreshToken(User user);
    Token buildToken(User user, long expiration);
}
