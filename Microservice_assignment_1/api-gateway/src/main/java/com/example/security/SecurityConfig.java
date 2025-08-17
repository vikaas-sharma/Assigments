package com.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

  private final JwtAuthFilter jwt;

  public SecurityConfig(JwtAuthFilter jwt) {
    this.jwt = jwt;
  }

  @Bean
  public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
    return http
      .csrf(ServerHttpSecurity.CsrfSpec::disable)
      .authorizeExchange(ex -> ex
        .pathMatchers("/auth/**","/actuator/**").permitAll()
        .anyExchange().authenticated()
      )
      .addFilterAt(jwt, org.springframework.security.web.server.SecurityWebFiltersOrder.AUTHENTICATION)
      .build();
  }
}
