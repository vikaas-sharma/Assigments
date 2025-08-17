package com.example.web;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/classrooms")
public class ClassroomController {
  private final KafkaTemplate<String, String> kafka;
  public ClassroomController(KafkaTemplate<String, String> kafka) { this.kafka = kafka; }

  @PostMapping
  public ResponseEntity<?> schedule(@RequestBody Map<String,Object> req) {
    kafka.send("class.created", req.toString());
    return ResponseEntity.ok(Map.of("status","scheduled","event","class.created"));
  }
}
