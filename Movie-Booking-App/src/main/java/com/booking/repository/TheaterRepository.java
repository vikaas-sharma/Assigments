package com.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.model.Theater;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long>{

}
