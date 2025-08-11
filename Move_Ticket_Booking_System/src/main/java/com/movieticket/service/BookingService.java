package com.movieticket.service;

import com.movieticket.dto.BookingDto;
import com.movieticket.entity.Booking;
import com.movieticket.entity.BookingStatus;
import com.movieticket.entity.Customer;
import com.movieticket.entity.Movie;
import com.movieticket.entity.Theater;
import com.movieticket.repository.BookingRepository;
import com.movieticket.repository.CustomerRepository;
import com.movieticket.repository.MovieRepository;
import com.movieticket.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookingService {
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private MovieRepository movieRepository;
    
    @Autowired
    private TheaterRepository theaterRepository;
    
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Booking not found with id: " + id));
    }
    
    public Booking createBooking(BookingDto bookingDto) {
        // Validate entities exist
        Customer customer = customerRepository.findById(bookingDto.getCustomerId())
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with id: " + bookingDto.getCustomerId()));
        
        Movie movie = movieRepository.findById(bookingDto.getMovieId())
                .orElseThrow(() -> new EntityNotFoundException("Movie not found with id: " + bookingDto.getMovieId()));
        
        Theater theater = theaterRepository.findById(bookingDto.getTheaterId())
                .orElseThrow(() -> new EntityNotFoundException("Theater not found with id: " + bookingDto.getTheaterId()));
        
        // Check seat availability
        if (!isSeatsAvailable(bookingDto.getTheaterId(), bookingDto.getShowTime(), bookingDto.getSeats())) {
            throw new IllegalArgumentException("Requested seats are not available for this show");
        }
        
        // Check if show time is in the future
        if (bookingDto.getShowTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Show time cannot be in the past");
        }
        
        Booking booking = new Booking(
            bookingDto.getShowTime(),
            bookingDto.getSeats(),
            movie,
            theater,
            customer
        );
        
        return bookingRepository.save(booking);
    }
    
    public Booking updateBooking(Long id, BookingDto bookingDto) {
        Booking existingBooking = getBookingById(id);
        
        // Only allow updates if booking is not completed
        if (existingBooking.getStatus() == BookingStatus.COMPLETED) {
            throw new IllegalArgumentException("Cannot update completed booking");
        }
        
        // Update fields
        if (bookingDto.getShowTime() != null) {
            if (bookingDto.getShowTime().isBefore(LocalDateTime.now())) {
                throw new IllegalArgumentException("Show time cannot be in the past");
            }
            existingBooking.setShowTime(bookingDto.getShowTime());
        }
        
        if (bookingDto.getSeats() != null) {
            // Check seat availability for new seat count
            if (!isSeatsAvailable(existingBooking.getTheater().getId(), 
                                existingBooking.getShowTime(), 
                                bookingDto.getSeats())) {
                throw new IllegalArgumentException("Requested seats are not available for this show");
            }
            existingBooking.setSeats(bookingDto.getSeats());
        }
        
        if (bookingDto.getStatus() != null) {
            existingBooking.setStatus(bookingDto.getStatus());
        }
        
        return bookingRepository.save(existingBooking);
    }
    
    public void deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new EntityNotFoundException("Booking not found with id: " + id);
        }
        bookingRepository.deleteById(id);
    }
    
    public List<Booking> getBookingsByCustomer(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }
    
    public List<Booking> getBookingsByMovie(Long movieId) {
        return bookingRepository.findByMovieId(movieId);
    }
    
    public List<Booking> getBookingsByTheater(Long theaterId) {
        return bookingRepository.findByTheaterId(theaterId);
    }
    
    public List<Booking> getBookingsByStatus(BookingStatus status) {
        return bookingRepository.findByStatus(status);
    }
    
    public List<Booking> getUpcomingBookingsByCustomer(Long customerId) {
        return bookingRepository.findUpcomingBookingsByCustomer(customerId, LocalDateTime.now());
    }
    
    public void cancelBooking(Long id) {
        Booking booking = getBookingById(id);
        if (booking.getStatus() == BookingStatus.COMPLETED) {
            throw new IllegalArgumentException("Cannot cancel completed booking");
        }
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }
    
    private boolean isSeatsAvailable(Long theaterId, LocalDateTime showTime, Integer requestedSeats) {
        Long bookedSeats = bookingRepository.countBookedSeatsForShow(theaterId, showTime);
        Theater theater = theaterRepository.findById(theaterId)
                .orElseThrow(() -> new EntityNotFoundException("Theater not found"));
        
        int availableSeats = (theater.getCapacity() != null ? theater.getCapacity() : 0) - bookedSeats.intValue();
        return availableSeats >= requestedSeats;
    }
}
