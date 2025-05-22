package dev.connectme.connectme.countryCode.infrastructure.dtos.in;

import dev.connectme.connectme.phone.domain.models.Phone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ToStoreCountryCodeDto {
    @NotBlank(message = "Country name is required")
    private String name;

    @NotBlank(message = "Country code is required")
    @Size(min = 2, max = 4, message = "Country code must be between 2 and 4 chars (e.g., +1, +52)")
    @Pattern(regexp = "\\+[1-9]\\d{0,2}", message = "Invalid country code (must start with '+' and have 1-3 digits)")
    private String code;

    private String flag;
}
