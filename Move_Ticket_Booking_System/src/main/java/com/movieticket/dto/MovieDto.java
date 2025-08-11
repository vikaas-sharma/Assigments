package com.movieticket.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

public class MovieDto {
    
    private Long id;
    
    @NotBlank(message = "Movie title is required")
    private String title;
    
    @NotBlank(message = "Movie genre is required")
    private String genre;
    
    private String description;
    
    @Positive(message = "Duration must be positive")
    private Integer duration;
    
    private List<Long> theaterIds;

    // Default constructor
    public MovieDto() {}

    // Constructor with required fields
    public MovieDto(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<Long> getTheaterIds() {
        return theaterIds;
    }

    public void setTheaterIds(List<Long> theaterIds) {
        this.theaterIds = theaterIds;
    }
}
