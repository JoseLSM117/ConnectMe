package dev.connectme.connectme.countryCode.infrastructure.entities;

import dev.connectme.connectme.countryCode.infrastructure.repositories.JpaCountryCodeRepository;
import dev.connectme.connectme.phone.domain.models.Phone;
import dev.connectme.connectme.phone.infrastructure.entities.PhoneEntity;
import dev.connectme.connectme.phone.infrastructure.repositories.JpaPhoneRepository;
import dev.connectme.connectme.user.infrastructure.entities.UserEntity;
import dev.connectme.connectme.user.infrastructure.repositories.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@RequiredArgsConstructor
public class CountryCodeEntityTest {

    @Autowired
    private JpaCountryCodeRepository jpaCountryCodeRepository;

    @Autowired
    private JpaPhoneRepository jpaPhoneRepository;

    @Autowired
    private JpaUserRepository jpaUserRepository;

    @Test
    void shouldInstanceCorrectly() {
        CountryCodeEntity countryCodeEntity = CountryCodeEntity.builder()
                .name("Mexico")
                .flag("mx")
                .code("+52")
                .build();

        CountryCodeEntity countryCodeEntitySaved = jpaCountryCodeRepository.save(countryCodeEntity);

        assertNotNull(countryCodeEntitySaved);
        assertEquals(countryCodeEntitySaved.getName(), "Mexico");
        assertEquals(countryCodeEntitySaved.getFlag(), "mx");
        assertEquals(countryCodeEntitySaved.getCode(), "+52");
    }

    @Test
    void shouldThrowExceptionIfNameIsNull() {
        assertThrows(Exception.class, () -> {
            CountryCodeEntity countryCodeEntity = CountryCodeEntity.builder()
                    .code("+52")
                    .flag("mx")
                    .build();
            jpaCountryCodeRepository.save(countryCodeEntity);
        });
    }

    @Test
    void shouldThrowExceptionIfCodeIsNull() {
        assertThrows(Exception.class, () -> {
            CountryCodeEntity countryCodeEntity = CountryCodeEntity.builder()
                    .name("Mexico")
                    .flag("mx")
                    .build();
            jpaCountryCodeRepository.save(countryCodeEntity);
        });
    }

    @Test
    @Transactional
    void shouldMaintainBidirectionalRelationWithPhone() {
        UserEntity user = UserEntity.builder()
                .email("test@test.com")
                .country("xd")
                .firstName("test")
                .lastName("Test2")
                .password("alv")
                .build();
        jpaUserRepository.save(user);

        CountryCodeEntity countryCodeEntity = CountryCodeEntity.builder()
                .name("Mexico")
                .flag("mx")
                .code("+52")
                .build();
        jpaCountryCodeRepository.save(countryCodeEntity);

        PhoneEntity phoneEntity = PhoneEntity.builder()
                .number("5569382066")
                .build();

        phoneEntity.setCountryCode(countryCodeEntity);
        phoneEntity.setUser(user);
        countryCodeEntity.setPhone(phoneEntity);
        user.setPhone(phoneEntity);

        jpaPhoneRepository.save(phoneEntity);

        CountryCodeEntity countryCodeFound = jpaCountryCodeRepository.findById(countryCodeEntity.getId()).orElseThrow();
        PhoneEntity phoneFound = jpaPhoneRepository.findById(phoneEntity.getId()).orElseThrow();

        assertNotNull(countryCodeFound.getPhone().getId());
        assertNotNull(phoneFound.getUser().getId());
    }
}
