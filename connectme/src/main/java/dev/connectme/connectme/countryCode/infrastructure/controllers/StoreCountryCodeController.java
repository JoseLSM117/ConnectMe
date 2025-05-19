package dev.connectme.connectme.countryCode.infrastructure.controllers;

import dev.connectme.connectme.countryCode.application.usecases.StoreUseCase;
import dev.connectme.connectme.countryCode.infrastructure.dtos.in.ToStoreCountryCodeDto;
import dev.connectme.connectme.countryCode.infrastructure.dtos.out.StoredCountryCodeDto;
import dev.connectme.connectme.shared.infrastructure.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/country-codes")
@RequiredArgsConstructor
public class StoreCountryCodeController {
    private final StoreUseCase storeUseCase;

    @PostMapping()
    public ResponseEntity<ApiResponse<StoredCountryCodeDto>> store(@Valid @RequestBody ToStoreCountryCodeDto toStoreCountryCodeDto){
        StoredCountryCodeDto storedCountryCodeDto = storeUseCase.execute(toStoreCountryCodeDto);
        ApiResponse<StoredCountryCodeDto> response = new ApiResponse<>(
                201,
                "Country Code Stored Correctly",
                storedCountryCodeDto
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
