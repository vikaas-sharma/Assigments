package com.example.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {
  @GetMapping("/users/me")
  public ResponseEntity<?> me(@RequestHeader(value="X-User-Email", required=false) String email) {
    return ResponseEntity.ok(Map.of("email", email));
  }
}
