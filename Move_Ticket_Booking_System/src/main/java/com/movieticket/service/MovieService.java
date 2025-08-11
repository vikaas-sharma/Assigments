package com.movieticket.service;

import com.movieticket.dto.MovieDto;
import com.movieticket.entity.Movie;
import com.movieticket.entity.Theater;
import com.movieticket.repository.MovieRepository;
import com.movieticket.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieService {
    
    @Autowired
    private MovieRepository movieRepository;
    
    @Autowired
    private TheaterRepository theaterRepository;
    
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }
    
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found with id: " + id));
    }
    
    public Movie createMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setGenre(movieDto.getGenre());
        movie.setDescription(movieDto.getDescription());
        movie.setDuration(movieDto.getDuration());
        
        // Set theaters if provided
        if (movieDto.getTheaterIds() != null && !movieDto.getTheaterIds().isEmpty()) {
            List<Theater> theaters = theaterRepository.findAllById(movieDto.getTheaterIds());
            movie.setTheaters(theaters);
        }
        
        return movieRepository.save(movie);
    }
    
    public Movie updateMovie(Long id, MovieDto movieDto) {
        Movie existingMovie = getMovieById(id);
        
        existingMovie.setTitle(movieDto.getTitle());
        existingMovie.setGenre(movieDto.getGenre());
        existingMovie.setDescription(movieDto.getDescription());
        existingMovie.setDuration(movieDto.getDuration());
        
        // Update theaters if provided
        if (movieDto.getTheaterIds() != null) {
            List<Theater> theaters = theaterRepository.findAllById(movieDto.getTheaterIds());
            existingMovie.setTheaters(theaters);
        }
        
        return movieRepository.save(existingMovie);
    }
    
    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new EntityNotFoundException("Movie not found with id: " + id);
        }
        movieRepository.deleteById(id);
    }
    
    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }
    
    public List<Movie> searchMoviesByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }
    
    public List<Movie> getMoviesByTheater(Long theaterId) {
        return movieRepository.findByTheaterId(theaterId);
    }
    
    public boolean movieExists(String title) {
        return movieRepository.existsByTitle(title);
    }
}
