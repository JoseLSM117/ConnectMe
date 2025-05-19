package dev.connectme.connectme.auth.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)  // Deshabilitar CSRF para APIs REST
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**", "/api/country-codes/**").permitAll() // Permitir acceso público
                        .anyRequest().authenticated()  // El resto requiere autenticación
                )
                .formLogin(AbstractHttpConfigurer::disable)  // Deshabilitar formulario de login (para API REST)
                .httpBasic(AbstractHttpConfigurer::disable); // Deshabilitar autenticación básica

        return http.build();

    }
}
