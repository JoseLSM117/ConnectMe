package dev.connectme.connectme.phone.infrastructure.mapper;


import dev.connectme.connectme.countryCode.infrastructure.entities.CountryCodeEntity;
import dev.connectme.connectme.phone.domain.models.Phone;
import dev.connectme.connectme.phone.infrastructure.entities.PhoneEntity;
import dev.connectme.connectme.user.infrastructure.entities.UserEntity;

public class PhoneEntityMapper {
    public static PhoneEntity fromDomain(Phone phone){
        return new PhoneEntity(
                phone.getId(),
                CountryCodeEntity.fromDomainModel(phone.getCountryCode()),
                phone.getNumber(),
                null
        );

    }
    public static Phone toDomain(PhoneEntity phoneEntity){
        return Phone.builder()
                .id(phoneEntity.getId())
                .countryCode(phoneEntity.getCountryCode().toDomainModel())
                .number(phoneEntity.getNumber())
                .build();
    }
}
