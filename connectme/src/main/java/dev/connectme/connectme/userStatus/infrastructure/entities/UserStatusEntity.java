package dev.connectme.connectme.userStatus.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.connectme.connectme.user.infrastructure.entities.UserEntity;
import dev.connectme.connectme.userStatus.domain.models.UserStatus;
import dev.connectme.connectme.userStatus.infrastructure.mapper.UserStatusEntityMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "userStatus")
public class UserStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus.StatusName statusName;

    @Column(nullable = false)
    private Date lastSeen;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @JsonBackReference("user-status")
    private UserEntity user;

    public UserStatus toDomain(){
        return UserStatusEntityMapper.toDomain(this);
    }
    public static UserStatusEntity fromDomainModel(UserStatus userStatus){
        return UserStatusEntityMapper.fromDomain(userStatus);
    }
}
