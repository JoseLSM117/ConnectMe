package dev.connectme.connectme.auth.application.ports;

import dev.connectme.connectme.auth.domain.models.Token;
import dev.connectme.connectme.phone.domain.models.Phone;
import dev.connectme.connectme.user.domain.models.User;

public interface TokenServicePort {
    Token generateAccessToken(Phone phone);
    Token generateRefreshToken(Phone phone);
    Token buildToken(Phone phone, long expiration, Token.TokenPurpose purpose);
}
