package com.movieticket.service;

import com.movieticket.dto.TheaterDto;
import com.movieticket.entity.Movie;
import com.movieticket.entity.Theater;
import com.movieticket.repository.MovieRepository;
import com.movieticket.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class TheaterService {
    
    @Autowired
    private TheaterRepository theaterRepository;
    
    @Autowired
    private MovieRepository movieRepository;
    
    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }
    
    public Theater getTheaterById(Long id) {
        return theaterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Theater not found with id: " + id));
    }
    
    public Theater createTheater(TheaterDto theaterDto) {
        Theater theater = new Theater();
        theater.setName(theaterDto.getName());
        theater.setLocation(theaterDto.getLocation());
        theater.setAddress(theaterDto.getAddress());
        theater.setCapacity(theaterDto.getCapacity());
        
        // Set movies if provided
        if (theaterDto.getMovieIds() != null && !theaterDto.getMovieIds().isEmpty()) {
            List<Movie> movies = movieRepository.findAllById(theaterDto.getMovieIds());
            theater.setMovies(movies);
        }
        
        return theaterRepository.save(theater);
    }
    
    public Theater updateTheater(Long id, TheaterDto theaterDto) {
        Theater existingTheater = getTheaterById(id);
        
        existingTheater.setName(theaterDto.getName());
        existingTheater.setLocation(theaterDto.getLocation());
        existingTheater.setAddress(theaterDto.getAddress());
        existingTheater.setCapacity(theaterDto.getCapacity());
        
        // Update movies if provided
        if (theaterDto.getMovieIds() != null) {
            List<Movie> movies = movieRepository.findAllById(theaterDto.getMovieIds());
            existingTheater.setMovies(movies);
        }
        
        return theaterRepository.save(existingTheater);
    }
    
    public void deleteTheater(Long id) {
        if (!theaterRepository.existsById(id)) {
            throw new EntityNotFoundException("Theater not found with id: " + id);
        }
        theaterRepository.deleteById(id);
    }
    
    public List<Theater> getTheatersByLocation(String location) {
        return theaterRepository.findByLocation(location);
    }
    
    public List<Theater> searchTheatersByName(String name) {
        return theaterRepository.findByNameContainingIgnoreCase(name);
    }
    
    public List<Theater> getTheatersByMovie(Long movieId) {
        return theaterRepository.findByMovieId(movieId);
    }
    
    public boolean theaterExists(String name, String location) {
        return theaterRepository.existsByNameAndLocation(name, location);
    }
}
