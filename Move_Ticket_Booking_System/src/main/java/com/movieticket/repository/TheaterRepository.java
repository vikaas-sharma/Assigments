package com.movieticket.repository;

import com.movieticket.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {
    
    List<Theater> findByLocation(String location);
    
    List<Theater> findByNameContainingIgnoreCase(String name);
    
    @Query("SELECT t FROM Theater t JOIN t.movies m WHERE m.id = :movieId")
    List<Theater> findByMovieId(@Param("movieId") Long movieId);
    
    boolean existsByNameAndLocation(String name, String location);
}
