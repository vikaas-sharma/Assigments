package com.example.web;

import com.example.domain.User;
import com.example.repo.UserRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final UserRepo users;
  private final BCryptPasswordEncoder enc = new BCryptPasswordEncoder();
  @Value("${jwt.secret}") String secret;

  public AuthController(UserRepo users) { this.users = users; }

  @PostMapping("/register")
  public ResponseEntity<?> register(@Valid @RequestBody User u) {
    u.setPassword(enc.encode(u.getPassword()));
    return ResponseEntity.ok(users.save(u));
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody Map<String,String> req) {
    var user = users.findByEmail(req.get("email")).orElse(null);
    if (user == null || !enc.matches(req.get("password"), user.getPassword()))
      return ResponseEntity.status(401).body(Map.of("error","invalid_credentials"));

    String token = Jwts.builder()
        .setSubject(user.getEmail())
        .claim("role", user.getRole())
        .setIssuedAt(new Date())
        .setExpiration(Date.from(Instant.now().plusSeconds(60*60*4)))
        .signWith(SignatureAlgorithm.HS256, secret.getBytes())
        .compact();
    return ResponseEntity.ok(Map.of("token", token));
  }
}
