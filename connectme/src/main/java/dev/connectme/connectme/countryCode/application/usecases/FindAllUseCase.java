package dev.connectme.connectme.countryCode.application.usecases;

import dev.connectme.connectme.countryCode.domain.models.CountryCode;
import dev.connectme.connectme.countryCode.domain.ports.CountryCodeRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllUseCase {
    private final CountryCodeRepositoryPort countryCodeRepositoryPort;
    public List<CountryCode> execute(){
        return countryCodeRepositoryPort.findAll();
    }
}
