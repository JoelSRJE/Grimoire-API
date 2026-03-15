package com.project.backend.config;

import com.project.backend.repositories.UserRepository;
import com.project.backend.utils.AuthenticationFilter;
import com.project.backend.utils.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserRepository userRepository;
    private final JWTService jwtService;

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http
            ) {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .addFilterBefore(
                        new AuthenticationFilter(jwtService, userRepository),
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}
