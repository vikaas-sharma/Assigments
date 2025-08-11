package com.movieticket.controller;

import com.movieticket.dto.TheaterDto;
import com.movieticket.entity.Theater;
import com.movieticket.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/theaters")
@CrossOrigin(origins = "*")
public class TheaterController {
    
    @Autowired
    private TheaterService theaterService;
    
    @GetMapping
    public ResponseEntity<List<Theater>> getAllTheaters() {
        List<Theater> theaters = theaterService.getAllTheaters();
        return ResponseEntity.ok(theaters);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Theater> getTheaterById(@PathVariable Long id) {
        try {
            Theater theater = theaterService.getTheaterById(id);
            return ResponseEntity.ok(theater);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping
    public ResponseEntity<Theater> createTheater(@Valid @RequestBody TheaterDto theaterDto) {
        try {
            Theater theater = theaterService.createTheater(theaterDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(theater);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Theater> updateTheater(@PathVariable Long id, @Valid @RequestBody TheaterDto theaterDto) {
        try {
            Theater theater = theaterService.updateTheater(id, theaterDto);
            return ResponseEntity.ok(theater);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheater(@PathVariable Long id) {
        try {
            theaterService.deleteTheater(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/location/{location}")
    public ResponseEntity<List<Theater>> getTheatersByLocation(@PathVariable String location) {
        List<Theater> theaters = theaterService.getTheatersByLocation(location);
        return ResponseEntity.ok(theaters);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Theater>> searchTheatersByName(@RequestParam String name) {
        List<Theater> theaters = theaterService.searchTheatersByName(name);
        return ResponseEntity.ok(theaters);
    }
    
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Theater>> getTheatersByMovie(@PathVariable Long movieId) {
        List<Theater> theaters = theaterService.getTheatersByMovie(movieId);
        return ResponseEntity.ok(theaters);
    }
}
