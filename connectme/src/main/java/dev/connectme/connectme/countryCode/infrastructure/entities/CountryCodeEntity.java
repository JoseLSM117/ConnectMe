package dev.connectme.connectme.countryCode.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import dev.connectme.connectme.countryCode.domain.models.CountryCode;
import dev.connectme.connectme.countryCode.infrastructure.mapper.CountryCodeEntityMapper;
import dev.connectme.connectme.phone.infrastructure.entities.PhoneEntity;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryCodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    @Column(nullable = true)
    private String flag;

    @ToString.Exclude
    @OneToOne(mappedBy = "countryCode")
    @JsonManagedReference
    private PhoneEntity phone;

    public static CountryCodeEntity fromDomainModel(CountryCode countryCode){
        return CountryCodeEntityMapper.fromDomain(countryCode);
    }
    public CountryCode toDomainModel(){
        return CountryCodeEntityMapper.toDomain(this);
    }
}
