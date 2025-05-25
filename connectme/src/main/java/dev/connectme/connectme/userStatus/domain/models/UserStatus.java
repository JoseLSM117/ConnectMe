package dev.connectme.connectme.userStatus.domain.models;

import dev.connectme.connectme.user.domain.models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
public class UserStatus {
    public enum StatusName {
        Active,
        Inactive
    }
    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotNull(message = "Status name cannot be null")
    private StatusName statusName;

    @NotNull(message = "Last seen date cannot be null")
    @PastOrPresent(message = "Last seen date must be in the past or present")
    private Date lastSeen;
}
