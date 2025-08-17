package com.example.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class JwtAuthFilter extends AuthenticationWebFilter {

    public JwtAuthFilter(@Value("${jwt.secret}") String secret) {
        super(authentication -> Mono.just(authentication));
        setServerAuthenticationConverter(exchange -> {
            String auth = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (auth != null && auth.startsWith("Bearer ")) {
                try {
                    String token = auth.substring(7);
                    Claims claims = Jwts.parserBuilder().setSigningKey(secret.getBytes()).build()
                            .parseClaimsJws(token).getBody();
                    String sub = claims.getSubject();
                    String role = claims.get("role", String.class);
                    AbstractAuthenticationToken at = new AbstractAuthenticationToken(
                            List.of(new SimpleGrantedAuthority(role == null ? "ROLE_USER" : role))) {
                        @Override public Object getCredentials() { return token; }
                        @Override public Object getPrincipal() { return sub; }
                    };
                    at.setAuthenticated(true);
                    return Mono.just(at);
                } catch (Exception e) {
                    return Mono.empty();
                }
            }
            return Mono.empty();
        });
    }
}
