package dev.connectme.connectme.countryCode.infrastructure.mapper;

import dev.connectme.connectme.countryCode.domain.models.CountryCode;
import dev.connectme.connectme.countryCode.infrastructure.entities.CountryCodeEntity;

public class CountryCodeEntityMapper {
    public static CountryCodeEntity fromDomain(CountryCode countryCode){
        return new CountryCodeEntity(
                countryCode.getId(),
                countryCode.getName(),
                countryCode.getCode(),
                countryCode.getFlag(),
                null
        );
    }
    public static CountryCode toDomain(CountryCodeEntity countryCodeEntity){
        return new CountryCode(
                countryCodeEntity.getId(),
                countryCodeEntity.getName(),
                countryCodeEntity.getCode(),
                countryCodeEntity.getFlag(),
                null
        );
    }
}
