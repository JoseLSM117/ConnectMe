package dev.connectme.connectme.phone.domain.models;

import dev.connectme.connectme.countryCode.domain.models.CountryCode;
import dev.connectme.connectme.user.domain.models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
public class Phone {

    @NotNull(message = "Phone id is required")
    private Long id;

    @NotNull(message = "Country Code is required")
    private CountryCode countryCode;

    @NotBlank(message = "Phone number is required")
    @Size(min = 4, max = 15, message = "Phone number must be between 4 and 15 digits")
    @Pattern(regexp = "[0-9]+", message = "Phone number must contain only digits (no spaces or symbols)")
    private String number;

    @ToString.Exclude
    @NotNull(message = "User is required in phone entity")
    private User user;
}
