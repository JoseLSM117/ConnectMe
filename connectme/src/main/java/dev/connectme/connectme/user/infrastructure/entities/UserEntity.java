package dev.connectme.connectme.user.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.connectme.connectme.auth.infrastructure.entities.TokenEntity;
import dev.connectme.connectme.phone.infrastructure.entities.PhoneEntity;
import dev.connectme.connectme.user.domain.models.User;
import dev.connectme.connectme.user.infrastructure.mapper.UserEntityMapper;
import dev.connectme.connectme.userStatus.infrastructure.entities.UserStatusEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String profilePicture;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    @Column(nullable = true)
    private String country;

    @Column(nullable = true)
    private User.Gender gender;

    @Column(nullable = true)
    private boolean isVerify = false;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-tokens")
    private List<TokenEntity> tokens = new ArrayList<>();

    @ToString.Exclude
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-status")
    private UserStatusEntity userStatus;

    @ToString.Exclude
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("user-phone")
    private PhoneEntity phone;

    public static UserEntity fromDomainModel(User user){
        return UserEntityMapper.fromDomain(user);
    }

    public User toDomainModel(){
        return UserEntityMapper.toDomain(this);
    }
    public void addToken(TokenEntity token) {
        token.setUser(this);
        this.tokens.add(token);
    }

}
