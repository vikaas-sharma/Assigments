package com.example.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@RestController
@RequestMapping("/health")
public class HealthController {
  @GetMapping
  public ResponseEntity<?> ok() {
    return ResponseEntity.ok(Map.of("service","assessment-service","status","UP"));
  }
}
