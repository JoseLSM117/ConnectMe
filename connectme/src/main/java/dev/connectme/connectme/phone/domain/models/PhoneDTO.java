package dev.connectme.connectme.phone.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class PhoneDTO {
    private Long id;
    private String countryCode;
    private String number;
}
