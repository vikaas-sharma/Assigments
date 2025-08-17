package com.example.web;

import com.example.domain.Course;
import com.example.repo.CourseRepo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CourseController {
  private final CourseRepo repo;
  public CourseController(CourseRepo repo) { this.repo = repo; }

  @PostMapping
  public ResponseEntity<?> create(@RequestBody Course c) {
    return ResponseEntity.ok(repo.save(c));
  }
}
