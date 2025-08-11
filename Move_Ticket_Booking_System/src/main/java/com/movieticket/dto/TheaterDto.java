package com.movieticket.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

public class TheaterDto {
    
    private Long id;
    
    @NotBlank(message = "Theater name is required")
    private String name;
    
    @NotBlank(message = "Theater location is required")
    private String location;
    
    private String address;
    
    @Positive(message = "Capacity must be positive")
    private Integer capacity;
    
    private List<Long> movieIds;

    // Default constructor
    public TheaterDto() {}

    // Constructor with required fields
    public TheaterDto(String name, String location) {
        this.name = name;
        this.location = location;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public List<Long> getMovieIds() {
        return movieIds;
    }

    public void setMovieIds(List<Long> movieIds) {
        this.movieIds = movieIds;
    }
}
