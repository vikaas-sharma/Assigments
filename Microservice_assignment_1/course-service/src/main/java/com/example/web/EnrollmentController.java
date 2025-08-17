package com.example.web;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
  private final KafkaTemplate<String, String> kafka;
  public EnrollmentController(KafkaTemplate<String, String> kafka) { this.kafka = kafka; }

  @PostMapping
  public ResponseEntity<?> enroll(@RequestBody Map<String,Object> req) {
    // naive, just emit event
    kafka.send("user.enrolled", req.toString());
    return ResponseEntity.ok(Map.of("status","enrolled", "event","user.enrolled"));
  }
}
