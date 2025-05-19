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
        System.out.println("Phone data");
        System.out.println(phone);
        PhoneEntity phoneEntity = PhoneEntity.fromDomain(phone);
        System.out.println("Phone Entity");
        System.out.println(phoneEntity);
        PhoneEntity phoneToSave = jpaPhoneRepository.save(phoneEntity);
        System.out.println("Phone Saved");
        System.out.println(phoneToSave);
        return phoneToSave.toDomain();
    }

    @Override
    public Optional<Phone> findByNumber(String number) {
        System.out.println("All");
        System.out.println(jpaPhoneRepository.findAll());
        System.out.println("MY");
        System.out.println(number);
        System.out.println("found");
        System.out.println(jpaPhoneRepository.findByNumber(number));
        return jpaPhoneRepository.findByNumber(number).map(PhoneEntity::toDomain);
    }
}
