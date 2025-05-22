package dev.connectme.connectme.user.infrastructure.mapper;

import dev.connectme.connectme.auth.domain.models.Token;
import dev.connectme.connectme.auth.infrastructure.mapper.TokenEntityMapper;
import dev.connectme.connectme.phone.infrastructure.entities.PhoneEntity;
import dev.connectme.connectme.user.domain.models.User;
import dev.connectme.connectme.user.infrastructure.entities.UserEntity;
import dev.connectme.connectme.userStatus.infrastructure.mapper.UserStatusEntityMapper;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserEntityMapper {

    public static UserEntity fromDomain(User user) {
        UserEntity userEntity = new UserEntity(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getProfilePicture(),
                user.getPassword(),
                user.getCreateAt(),
                user.getUpdateAt(),
                user.getCountry(),
                user.getGender(),
                user.isVerify(),
                new ArrayList<>(),
                user.getUserStatus() != null ? UserStatusEntityMapper.fromDomain(user.getUserStatus()): null,
                user.getPhone() != null ? PhoneEntity.fromDomain(user.getPhone()) : null
        );
        if (userEntity.getUserStatus() != null) {
            userEntity.getUserStatus().setUser(userEntity);
        }
        if(userEntity.getPhone() != null){
            userEntity.getPhone().setUser(userEntity);
        }
        return userEntity;
    }

    @Transactional
    public static User toDomain(UserEntity entity) {
        List<Token> tokens = Collections.emptyList();
        if (entity.getTokens() != null) {
            tokens = entity.getTokens().stream()
                    .map(TokenEntityMapper::toDomain)
                    .collect(Collectors.toList());
        }

        return new User(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getCountry(),
                entity.getGender(),
                entity.isVerify(),
                entity.getPassword(),
                entity.getProfilePicture(),
                entity.getCreateAt(),
                entity.getUpdateAt(),
                tokens,
                entity.getUserStatus() != null ? entity.getUserStatus().toDomain() : null,
                entity.getPhone() != null ? entity.getPhone().toDomain():null
        );
    }
}
