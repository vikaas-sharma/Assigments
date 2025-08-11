package com.movieticket.config;

import com.movieticket.entity.*;
import com.movieticket.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private MovieRepository movieRepository;
    
    @Autowired
    private TheaterRepository theaterRepository;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @Override
    public void run(String... args) throws Exception {
        // Initialize sample data
        initializeMovies();
        initializeTheaters();
        initializeCustomers();
        initializeBookings();
        
        System.out.println("Sample data initialized successfully!");
    }
    
    private void initializeMovies() {
        Movie movie1 = new Movie("The Avengers", "Action");
        movie1.setDescription("Earth's mightiest heroes must come together and learn to fight as a team.");
        movie1.setDuration(143);
        
        Movie movie2 = new Movie("Inception", "Sci-Fi");
        movie2.setDescription("A thief who steals corporate secrets through dream-sharing technology.");
        movie2.setDuration(148);
        
        Movie movie3 = new Movie("The Dark Knight", "Action");
        movie3.setDescription("When the menace known as the Joker wreaks havoc and chaos on Gotham City.");
        movie3.setDuration(152);
        
        movieRepository.saveAll(Arrays.asList(movie1, movie2, movie3));
    }
    
    private void initializeTheaters() {
        Theater theater1 = new Theater("Cineplex Downtown", "Downtown");
        theater1.setAddress("123 Main Street, Downtown");
        theater1.setCapacity(200);
        
        Theater theater2 = new Theater("Multiplex Cinema", "Uptown");
        theater2.setAddress("456 Oak Avenue, Uptown");
        theater2.setCapacity(150);
        
        Theater theater3 = new Theater("Starlight Theater", "Midtown");
        theater3.setAddress("789 Pine Road, Midtown");
        theater3.setCapacity(180);
        
        theaterRepository.saveAll(Arrays.asList(theater1, theater2, theater3));
    }
    
    private void initializeCustomers() {
        Customer customer1 = new Customer("John Doe", "john.doe@email.com");
        customer1.setPhone("1234567890");
        
        Customer customer2 = new Customer("Jane Smith", "jane.smith@email.com");
        customer2.setPhone("0987654321");
        
        Customer customer3 = new Customer("Bob Johnson", "bob.johnson@email.com");
        customer3.setPhone("5555555555");
        
        customerRepository.saveAll(Arrays.asList(customer1, customer2, customer3));
    }
    
    private void initializeBookings() {
        // Get entities
        Movie movie1 = movieRepository.findByTitle("The Avengers").get(0);
        Movie movie2 = movieRepository.findByTitle("Inception").get(0);
        
        Theater theater1 = theaterRepository.findByName("Cineplex Downtown").get(0);
        Theater theater2 = theaterRepository.findByName("Multiplex Cinema").get(0);
        
        Customer customer1 = customerRepository.findByEmail("john.doe@email.com");
        Customer customer2 = customerRepository.findByEmail("jane.smith@email.com");
        
        // Create sample bookings
        LocalDateTime futureTime1 = LocalDateTime.now().plusDays(1).withHour(19).withMinute(0);
        LocalDateTime futureTime2 = LocalDateTime.now().plusDays(2).withHour(20).withMinute(30);
        
        Booking booking1 = new Booking(futureTime1, 2, movie1, theater1, customer1);
        Booking booking2 = new Booking(futureTime2, 3, movie2, theater2, customer2);
        
        bookingRepository.saveAll(Arrays.asList(booking1, booking2));
    }
}
