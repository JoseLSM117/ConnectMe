package dev.connectme.connectme.auth.application.usecases;

import dev.connectme.connectme.auth.application.ports.TokenServicePort;
import dev.connectme.connectme.auth.domain.exceptions.UserAlreadyRegisteredException;
import dev.connectme.connectme.auth.infrastructure.dtos.in.RegisterUserDTORequest;
import dev.connectme.connectme.auth.infrastructure.dtos.out.RegisterUserDtoResponse;
import dev.connectme.connectme.countryCode.domain.models.CountryCode;
import dev.connectme.connectme.countryCode.domain.ports.CountryCodeRepositoryPort;
import dev.connectme.connectme.phone.domain.models.Phone;
import dev.connectme.connectme.phone.domain.ports.PhoneRepositoryPort;
import dev.connectme.connectme.user.domain.models.User;
import dev.connectme.connectme.user.domain.ports.out.UserRepositoryPort;
import dev.connectme.connectme.userStatus.domain.models.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterUseCase {
    private final PhoneRepositoryPort phoneRepositoryPort;
    private final PasswordEncoder passwordEncoder;
    private final CountryCodeRepositoryPort countryCodeRepositoryPort;
    private final UserRepositoryPort userRepositoryPort;

    @Transactional
    public RegisterUserDtoResponse execute(RegisterUserDTORequest newUserInfo){
        Optional<Phone> userFoundWithPhone = phoneRepositoryPort.findByNumber(newUserInfo.getPhoneNumber());

        //Verify if exist an user with phoneNumberRegister
        if (userFoundWithPhone.isPresent()) {
            throw new UserAlreadyRegisteredException();
        }

        //Create user status
        UserStatus userStatus = UserStatus.builder()
                .statusName(UserStatus.StatusName.Inactive)
                .lastSeen(new Date())
                .build();

        //Find country code by id
        CountryCode countryCode = countryCodeRepositoryPort.findById(newUserInfo.getCountryCodeId()).orElseThrow();

        //Encode password
        String passwordEncoded = passwordEncoder.encode(newUserInfo.getPassword());

        //Create new phone number
        Phone phone = Phone.builder()
                .countryCode(countryCode)
                .number(newUserInfo.getPhoneNumber())
                .build();

        //Create new user
        User user = User.builder()
                .firstName(newUserInfo.getFirstName())
                .lastName(newUserInfo.getLastName())
                .email(newUserInfo.getEmail())
                .password(passwordEncoded)
                .userStatus(userStatus)
                .phone(phone)
                .build();
        User userStored = userRepositoryPort.save(user);
        System.out.println(userStored);
        return RegisterUserDtoResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .countryCode(phone.getCountryCode().getCode())
                .phoneNumber(phone.getNumber())
                .build();
    }

}
