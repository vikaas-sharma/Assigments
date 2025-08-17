package com.example.web;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/assessments")
public class AssessmentController {
  private final KafkaTemplate<String, String> kafka;
  public AssessmentController(KafkaTemplate<String, String> kafka) { this.kafka = kafka; }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody Map<String,Object> req) {
    kafka.send("assignment.due", req.toString());
    return ResponseEntity.ok(Map.of("status","created","event","assignment.due"));
  }

  @PostMapping("/{id}/submit")
  public ResponseEntity<?> submit(@PathVariable String id, @RequestBody Map<String,Object> req) {
    req.put("assignmentId", id);
    kafka.send("assignment.submitted", req.toString());
    return ResponseEntity.ok(Map.of("status","submitted","event","assignment.submitted"));
  }
}
