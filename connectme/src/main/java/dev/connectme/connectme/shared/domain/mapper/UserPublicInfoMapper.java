package dev.connectme.connectme.shared.domain.mapper;

import dev.connectme.connectme.shared.domain.entities.UserPublicInfoDto;
import dev.connectme.connectme.user.infrastructure.entities.UserEntity;

public class UserPublicInfoMapper {
    public static UserPublicInfoDto toDtoUserPublicInfo(UserEntity user){
        return new UserPublicInfoDto(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getProfilePicture(),
                user.getCreateAt(),
                user.getUpdateAt(),
                user.getCountry(),
                user.getGender(),
                user.isVerify()
        );
    }
}
