package br.com.suafinanca.finance_api.security;

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
            .csrf(csrf -> csrf.disable()) // Desabilita CSRF para APIs stateless
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 
            .authorizeHttpRequests(authorize -> authorize 

                .requestMatchers("/api/auth/**").permitAll() // Endpoints de autenticação são públicos
                .anyRequest().authenticated() // Todas as outras requisições exigem autenticação
            );
            //  O filtro JWT vai aqui
            
        return http.build();
    }

    @Bean //Por favor AGORA VAI
    public PasswordEncoder passwordEncoder() { 
        return new BCryptPasswordEncoder();
    }
}