package dev.connectme.connectme.countryCode.infrastructure.dtos.out;

import dev.connectme.connectme.phone.domain.models.Phone;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class StoredCountryCodeDto {
    private Long id;
    private String name;
    private String code;
    private String flag;
}
