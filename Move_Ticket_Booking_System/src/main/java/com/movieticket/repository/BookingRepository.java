package com.movieticket.repository;

import com.movieticket.entity.Booking;
import com.movieticket.entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    
    List<Booking> findByCustomerId(Long customerId);
    
    List<Booking> findByMovieId(Long movieId);
    
    List<Booking> findByTheaterId(Long theaterId);
    
    List<Booking> findByStatus(BookingStatus status);
    
    List<Booking> findByShowTimeBetween(LocalDateTime startTime, LocalDateTime endTime);
    
    @Query("SELECT b FROM Booking b WHERE b.customer.id = :customerId AND b.showTime >= :currentTime")
    List<Booking> findUpcomingBookingsByCustomer(@Param("customerId") Long customerId, @Param("currentTime") LocalDateTime currentTime);
    
    @Query("SELECT COUNT(b) FROM Booking b WHERE b.theater.id = :theaterId AND b.showTime = :showTime AND b.status = 'CONFIRMED'")
    Long countBookedSeatsForShow(@Param("theaterId") Long theaterId, @Param("showTime") LocalDateTime showTime);
}
