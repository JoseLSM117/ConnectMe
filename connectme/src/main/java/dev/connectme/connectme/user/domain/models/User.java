package dev.connectme.connectme.user.domain.models;

import dev.connectme.connectme.auth.domain.models.Token;
import dev.connectme.connectme.phone.domain.models.Phone;
import dev.connectme.connectme.userStatus.domain.models.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class User {
    public enum Gender {
        FEMALE,
        MALE
    }

    private Long id;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @Email(message = "Please provide a valid email address")
    private String email;

    private String country;

    private Gender gender;

    @NotNull(message = "Verify is required")
    private boolean isVerify;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String password;

    @Size(max = 255, message = "Profile picture URL is too long")
    private String profilePicture;

    private Date createAt;

    private Date updateAt;

    private List<Token> tokens;

    private UserStatus userStatus;

    private Phone phone;

}
