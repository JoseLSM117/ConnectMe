package dev.connectme.connectme.countryCode.application.init;

import dev.connectme.connectme.countryCode.domain.models.CountryCode;
import dev.connectme.connectme.countryCode.domain.ports.CountryCodeRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class CountryCodeInitializer implements CommandLineRunner {
    private final CountryCodeRepositoryPort jpaCountryCodeRepository;

    @Override
    public void run(String... args) throws Exception {
        if(!jpaCountryCodeRepository.isEmpty()){
            return;
        }
        List<CountryCode> defaultCountries = Arrays.asList(
                // América
                CountryCode.builder().name("United States").code("+1").flag("\uD83C\uDDFA\uD83C\uDDF8").build(),
                CountryCode.builder().name("Canada").code("+1").flag("\uD83C\uDDE8\uD83C\uDDF6").build(),
                CountryCode.builder().name("Mexico").code("+52").flag("\uD83C\uDDF2\uD83C\uDDFD").build(),
                CountryCode.builder().name("Brazil").code("+55").flag("\uD83C\uDDE7\uD83C\uDDF7").build(),
                CountryCode.builder().name("Argentina").code("+54").flag("\uD83C\uDDE6\uD83C\uDDF7").build(),
                CountryCode.builder().name("Colombia").code("+57").flag("\uD83C\uDDE8\uD83C\uDDF4").build(),
                CountryCode.builder().name("Chile").code("+56").flag("\uD83C\uDDE8\uD83C\uDDF1").build(),
                CountryCode.builder().name("Peru").code("+51").flag("\uD83C\uDDF5\uD83C\uDDEA").build(),
                CountryCode.builder().name("Venezuela").code("+58").flag("\uD83C\uDDFB\uD83C\uDDEA").build(),
                CountryCode.builder().name("Ecuador").code("+593").flag("\uD83C\uDDEA\uD83C\uDDE8").build(),

                // Europa
                CountryCode.builder().name("Spain").code("+34").flag("\uD83C\uDDEA\uD83C\uDDF8").build(),
                CountryCode.builder().name("France").code("+33").flag("\uD83C\uDDEB\uD83C\uDDF7").build(),
                CountryCode.builder().name("Germany").code("+49").flag("\uD83C\uDDE9\uD83C\uDDEA").build(),
                CountryCode.builder().name("Italy").code("+39").flag("\uD83C\uDDEE\uD83C\uDDF9").build(),
                CountryCode.builder().name("United Kingdom").code("+44").flag("\uD83C\uDDEC\uD83C\uDDE7").build(),
                CountryCode.builder().name("Portugal").code("+351").flag("\uD83C\uDDF5\uD83C\uDDF9").build(),
                CountryCode.builder().name("Netherlands").code("+31").flag("\uD83C\uDDF3\uD83C\uDDF1").build(),
                CountryCode.builder().name("Switzerland").code("+41").flag("\uD83C\uDDE8\uD83C\uDDED").build(),

                // Asia
                CountryCode.builder().name("China").code("+86").flag("\uD83C\uDDE8\uD83C\uDDF3").build(),
                CountryCode.builder().name("Japan").code("+81").flag("\uD83C\uDDEF\uD83C\uDDF5").build(),
                CountryCode.builder().name("India").code("+91").flag("\uD83C\uDDEE\uD83C\uDDF3").build(),
                CountryCode.builder().name("South Korea").code("+82").flag("\uD83C\uDDF0\uD83C\uDDF7").build(),
                CountryCode.builder().name("Singapore").code("+65").flag("\uD83C\uDDF8\uD83C\uDDEC").build(),

                // África
                CountryCode.builder().name("South Africa").code("+27").flag("\uD83C\uDDFF\uD83C\uDDE6").build(),
                CountryCode.builder().name("Egypt").code("+20").flag("\uD83C\uDDEA\uD83C\uDDEC").build(),
                CountryCode.builder().name("Nigeria").code("+234").flag("\uD83C\uDDF3\uD83C\uDDEC").build(),
                CountryCode.builder().name("Kenya").code("+254").flag("\uD83C\uDDF0\uD83C\uDDEA").build(),

                // Oceanía
                CountryCode.builder().name("Australia").code("+61").flag("\uD83C\uDDE6\uD83C\uDDFA").build(),
                CountryCode.builder().name("New Zealand").code("+64").flag("\uD83C\uDDF3\uD83C\uDDFF").build()
        );
        jpaCountryCodeRepository.saveAll(defaultCountries);
    }
}
