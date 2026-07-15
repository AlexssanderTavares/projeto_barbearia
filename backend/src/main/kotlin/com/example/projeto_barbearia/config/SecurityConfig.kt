package com.example.projeto_barbearia.config


import jakarta.servlet.DispatcherType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http.csrf {
            it.disable()
        }.cors {
            it.configure(http)
        }.sessionManagement {
            it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }.authorizeHttpRequests {
            it.dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                //configure routes below here
                .requestMatchers(HttpMethod.POST, "/client/new").permitAll()
                .requestMatchers(HttpMethod.POST, "/client/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/client/list").permitAll()
                .requestMatchers(HttpMethod.GET, "/client/get/email").permitAll()
                .requestMatchers(HttpMethod.PATCH, "/client/update").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/client/delete").permitAll()
                .anyRequest().authenticated()
        }.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authManager(authConfig: AuthenticationConfiguration) : AuthenticationManager {
        return authConfig.authenticationManager
    }
}