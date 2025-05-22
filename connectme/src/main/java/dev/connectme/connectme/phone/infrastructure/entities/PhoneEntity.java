package dev.connectme.connectme.phone.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.connectme.connectme.countryCode.infrastructure.entities.CountryCodeEntity;
import dev.connectme.connectme.phone.domain.models.Phone;
import dev.connectme.connectme.phone.infrastructure.mapper.PhoneEntityMapper;
import dev.connectme.connectme.user.infrastructure.entities.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "country_code_id", nullable = false, unique = true)
    private CountryCodeEntity countryCode;

    @Column(nullable = false)
    private String number;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @JsonBackReference("user-phone")
    private UserEntity user;

    public static PhoneEntity fromDomain(Phone phone){
        return PhoneEntityMapper.fromDomain(phone);
    }

    public Phone toDomain(){
        return PhoneEntityMapper.toDomain(this);
    }
}
