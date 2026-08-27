package com.placementhub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                .requestMatchers("/api/test/**").permitAll()

                .requestMatchers("/api/jobs/**").permitAll()
                
                .requestMatchers("/api/students/**").permitAll()
                
                .requestMatchers("/api/auth/superset-login").permitAll()

                .anyRequest().authenticated()
            );

        return http.build();
    }
}