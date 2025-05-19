package dev.connectme.connectme.countryCode.infrastructure.controllers;

import dev.connectme.connectme.countryCode.application.usecases.FindAllUseCase;
import dev.connectme.connectme.countryCode.domain.models.CountryCode;
import dev.connectme.connectme.shared.infrastructure.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/country-codes")
@RequiredArgsConstructor
public class FindAllCountryCodesController {
    private final FindAllUseCase findAllUseCase;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CountryCode>>> findAll(){
        List<CountryCode> allCountryCodes = findAllUseCase.execute();
        ApiResponse<List<CountryCode>> response = new ApiResponse<>(
                200,
                "Success",
                allCountryCodes
        );
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }
}
