package dev.connectme.connectme.phone.domain.ports;

import dev.connectme.connectme.phone.domain.models.Phone;

import java.util.Optional;

public interface PhoneRepositoryPort {
    Phone save(Phone phone);
    Optional<Phone> findByNumber(String number);
}
