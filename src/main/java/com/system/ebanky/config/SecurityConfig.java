package com.system.ebanky.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Désactive CSRF pour simplifier les tests avec Postman (optionnel)
                .csrf().disable()

                // Autorisations basées sur les rôles
                .authorizeHttpRequests(auth -> auth
                        .antMatchers("/admin/**").hasRole("ADMIN")
                        .antMatchers("/employee/**").hasRole("EMPLOYEE")
                        .antMatchers("/user/**").hasRole("USER")
                        .anyRequest().authenticated()
                )

                // Basic Auth pour l'ADMIN
                .httpBasic()

                // JWT pour USER et EMPLOYEE
                .and()
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless pour JWT
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


