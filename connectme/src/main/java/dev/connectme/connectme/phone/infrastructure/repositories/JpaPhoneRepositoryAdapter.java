package dev.connectme.connectme.phone.infrastructure.repositories;

import dev.connectme.connectme.phone.domain.models.Phone;
import dev.connectme.connectme.phone.domain.ports.PhoneRepositoryPort;
import dev.connectme.connectme.phone.infrastructure.entities.PhoneEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
@RequiredArgsConstructor
public class JpaPhoneRepositoryAdapter implements PhoneRepositoryPort {
    private final JpaPhoneRepository jpaPhoneRepository;

    @Transactional
    @Override
    public Phone save(Phone phone) {
        PhoneEntity phoneEntity = PhoneEntity.fromDomain(phone);
        PhoneEntity phoneToSave = jpaPhoneRepository.save(phoneEntity);
        return phoneToSave.toDomain();
    }

    @Override
    public Optional<Phone> findByNumber(String number) {
        return jpaPhoneRepository.findByNumber(number).map(PhoneEntity::toDomain);
    }
}
