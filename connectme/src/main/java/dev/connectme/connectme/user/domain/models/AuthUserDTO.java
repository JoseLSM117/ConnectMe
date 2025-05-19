package dev.connectme.connectme.user.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthUserDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String profilePicture;
}
