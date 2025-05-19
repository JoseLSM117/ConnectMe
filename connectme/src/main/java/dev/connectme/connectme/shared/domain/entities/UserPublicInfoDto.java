package dev.connectme.connectme.shared.domain.entities;

import dev.connectme.connectme.user.domain.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserPublicInfoDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String profilePicture;
    private Date createAt;
    private Date updateAt;
    private String country;
    private User.Gender gender;
    private boolean isVerify;
}
