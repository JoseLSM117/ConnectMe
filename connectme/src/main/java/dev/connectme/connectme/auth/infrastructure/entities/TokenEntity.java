package dev.connectme.connectme.auth.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.connectme.connectme.auth.domain.models.Token;
import dev.connectme.connectme.auth.infrastructure.mapper.TokenEntityMapper;
import dev.connectme.connectme.user.infrastructure.entities.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "token")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private boolean expired;

    @Column(nullable = false)
    private boolean revoked;

    @Column(name = "token_type", nullable = false)
    private Token.TokenType tokenType;

    @Column(name = "token_purpose", nullable = false)
    private Token.TokenPurpose tokenPurpose;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference("user-tokens")
    private UserEntity user;

    public Token toDomainModel() {
        return TokenEntityMapper.toDomain(this);
    }

    public static TokenEntity fromDomainModel(Token token) {
        return TokenEntityMapper.fromDomain(token);
    }
}