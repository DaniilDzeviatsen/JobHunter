package com.example.jobhunter.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.jobhunter.model.security.AccessTokenProperties;
import com.example.jobhunter.model.security.AccountRole;
import com.example.jobhunter.service.AccessTokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AccessTokenService accessTokenService) throws Exception {
        return httpSecurity
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(config -> config.disable())
                .authorizeHttpRequests(config -> config
                        .requestMatchers("/api-docs/**").permitAll()
                        .requestMatchers("/public-api/**").permitAll()
                        .requestMatchers("/candidate-api/**").hasRole(AccountRole.CANDIDATE.name())
                        .requestMatchers("/employer-api/**").hasRole(AccountRole.EMPLOYER.name())
                        .anyRequest().denyAll()
                )
                .addFilterAfter(new AccessTokenAuthenticationFilter(accessTokenService), BasicAuthenticationFilter.class)
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    Algorithm jwtAlgorithm(AccessTokenProperties props) {
        return Algorithm.HMAC256(props.getSecret());
    }

    @Bean
    JWTVerifier jwtVerifier(Algorithm algorithm) {
        return JWT.require(algorithm).build();
    }
}
