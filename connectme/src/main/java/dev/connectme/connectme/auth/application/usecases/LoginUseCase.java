package dev.connectme.connectme.auth.application.usecases;

import dev.connectme.connectme.auth.domain.exceptions.PasswordIsWrongException;
import dev.connectme.connectme.auth.domain.exceptions.UserNotRegisteredException;
import dev.connectme.connectme.auth.domain.models.Token;
import dev.connectme.connectme.auth.infrastructure.adapters.JwtService;
import dev.connectme.connectme.auth.infrastructure.dtos.in.LoginUserDtoRequest;
import dev.connectme.connectme.auth.infrastructure.repositories.JpaTokenRepositoryAdapter;
import dev.connectme.connectme.phone.domain.models.Phone;
import dev.connectme.connectme.phone.infrastructure.repositories.JpaPhoneRepositoryAdapter;
import dev.connectme.connectme.user.domain.models.User;
import dev.connectme.connectme.user.infrastructure.repositories.JpaUserRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginUseCase {

    @Autowired
    private JpaPhoneRepositoryAdapter jpaPhoneRepositoryAdapter;

    @Autowired
    private JpaUserRepositoryAdapter jpaUserRepositoryAdapter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JpaTokenRepositoryAdapter jpaTokenRepositoryAdapter;

    @Autowired
    private JwtService jwtService;

    @Transactional
    public User execute(LoginUserDtoRequest user) {
        Optional<Phone> phoneFound = jpaPhoneRepositoryAdapter.findByNumber(user.getPhoneNumber());

        if(phoneFound.isEmpty()){
            throw new UserNotRegisteredException();
        }

        User userFound = phoneFound.get().getUser();

        boolean passwordIsOk = passwordEncoder.matches(user.getPassword(), userFound.getPassword());
        if(!passwordIsOk){
            throw  new PasswordIsWrongException();
        }

        jpaTokenRepositoryAdapter.deleteAllTokensByUserId(userFound.getId());

        Token accessToken = jwtService.generateAccessToken(phoneFound.get());
        Token refreshToken = jwtService.generateRefreshToken(phoneFound.get());

        List<Token> tokens = List.of(accessToken, refreshToken);
        userFound.setTokens(tokens);
        jpaUserRepositoryAdapter.save(userFound);

        return userFound;
    }
}
