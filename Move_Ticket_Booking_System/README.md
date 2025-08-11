# Movie Ticket Booking System

A comprehensive Spring Boot application for managing movie ticket bookings with support for movies, theaters, customers, and bookings.

## Features

- **Movie Management**: CRUD operations for movies with genre, duration, and description
- **Theater Management**: CRUD operations for theaters with location, address, and capacity
- **Customer Management**: Customer registration and profile management
- **Booking System**: Ticket booking with seat availability checking
- **RESTful API**: Complete REST API for all operations
- **Data Validation**: Input validation and business rule enforcement
- **H2 Database**: In-memory database for development and testing

## Technology Stack

- **Backend**: Spring Boot 2.7.0
- **Database**: H2 (In-Memory)
- **ORM**: Spring Data JPA with Hibernate
- **Validation**: Bean Validation (JSR-303)
- **Build Tool**: Maven
- **Java Version**: 11

## Project Structure

```
src/main/java/com/movieticket/
├── entity/           # JPA entities
├── repository/       # Data access layer
├── service/         # Business logic layer
├── controller/      # REST API controllers
├── dto/            # Data Transfer Objects
└── config/         # Configuration classes
```

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

### Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd movie-ticket-booking-system
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`

### Database Access

- H2 Console: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:movieticketdb`
- Username: `sa`
- Password: `password`

## API Endpoints

### Movie Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/movies` | Get all movies |
| GET | `/api/movies/{id}` | Get movie by ID |
| POST | `/api/movies` | Create a new movie |
| PUT | `/api/movies/{id}` | Update movie by ID |
| DELETE | `/api/movies/{id}` | Delete movie by ID |
| GET | `/api/movies/genre/{genre}` | Get movies by genre |
| GET | `/api/movies/search?title={title}` | Search movies by title |
| GET | `/api/movies/theater/{theaterId}` | Get movies by theater |

### Theater Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/theaters` | Get all theaters |
| GET | `/api/theaters/{id}` | Get theater by ID |
| POST | `/api/theaters` | Create a new theater |
| PUT | `/api/theaters/{id}` | Update theater by ID |
| DELETE | `/api/theaters/{id}` | Delete theater by ID |
| GET | `/api/theaters/location/{location}` | Get theaters by location |
| GET | `/api/theaters/search?name={name}` | Search theaters by name |
| GET | `/api/theaters/movie/{movieId}` | Get theaters by movie |

### Customer Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/customers` | Get all customers |
| GET | `/api/customers/{id}` | Get customer by ID |
| POST | `/api/customers` | Create a new customer |
| PUT | `/api/customers/{id}` | Update customer by ID |
| DELETE | `/api/customers/{id}` | Delete customer by ID |
| GET | `/api/customers/email/{email}` | Get customer by email |
| GET | `/api/customers/search?name={name}` | Search customers by name |

### Booking Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/bookings` | Get all bookings |
| GET | `/api/bookings/{id}` | Get booking by ID |
| POST | `/api/bookings` | Create a new booking |
| PUT | `/api/bookings/{id}` | Update booking by ID |
| DELETE | `/api/bookings/{id}` | Delete booking by ID |
| GET | `/api/bookings/customer/{customerId}` | Get bookings by customer |
| GET | `/api/bookings/movie/{movieId}` | Get bookings by movie |
| GET | `/api/bookings/theater/{theaterId}` | Get bookings by theater |
| GET | `/api/bookings/status/{status}` | Get bookings by status |
| GET | `/api/bookings/customer/{customerId}/upcoming` | Get upcoming bookings by customer |
| POST | `/api/bookings/{id}/cancel` | Cancel a booking |

## Data Models

### Movie Entity
- `id`: Unique identifier
- `title`: Movie title
- `genre`: Movie genre
- `description`: Movie description
- `duration`: Movie duration in minutes
- `theaters`: List of theaters showing the movie

### Theater Entity
- `id`: Unique identifier
- `name`: Theater name
- `location`: Theater location
- `address`: Theater address
- `capacity`: Theater seating capacity
- `movies`: List of movies shown at the theater

### Customer Entity
- `id`: Unique identifier
- `name`: Customer name
- `email`: Customer email (unique)
- `phone`: Customer phone number
- `bookings`: List of customer's bookings

### Booking Entity
- `id`: Unique identifier
- `bookingDate`: Date and time of booking
- `showTime`: Date and time of the show
- `seats`: Number of seats booked
- `status`: Booking status (CONFIRMED, CANCELLED, COMPLETED, PENDING)
- `movie`: Associated movie
- `theater`: Associated theater
- `customer`: Associated customer

## Sample API Requests

### Create a Movie
```bash
curl -X POST http://localhost:8080/api/movies \
  -H "Content-Type: application/json" \
  -d '{
    "title": "The Matrix",
    "genre": "Sci-Fi",
    "description": "A computer programmer discovers a mysterious world.",
    "duration": 136
  }'
```

### Create a Customer
```bash
curl -X POST http://localhost:8080/api/customers \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Alice Johnson",
    "email": "alice.johnson@email.com",
    "phone": "1234567890"
  }'
```

### Create a Booking
```bash
curl -X POST http://localhost:8080/api/bookings \
  -H "Content-Type: application/json" \
  -d '{
    "showTime": "2024-01-15T19:00:00",
    "seats": 2,
    "movieId": 1,
    "theaterId": 1,
    "customerId": 1
  }'
```

## Business Rules

1. **Seat Availability**: System checks seat availability before confirming bookings
2. **Show Time Validation**: Bookings cannot be made for past show times
3. **Email Uniqueness**: Customer emails must be unique
4. **Booking Status**: Completed bookings cannot be updated or cancelled
5. **Data Integrity**: Foreign key relationships are maintained

## Sample Data

The application comes with pre-loaded sample data:
- 3 sample movies (The Avengers, Inception, The Dark Knight)
- 3 sample theaters (Cineplex Downtown, Multiplex Cinema, Starlight Theater)
- 3 sample customers (John Doe, Jane Smith, Bob Johnson)
- 2 sample bookings

## Testing

### Manual Testing
1. Start the application
2. Access H2 console at `http://localhost:8080/h2-console`
3. Use the sample data to test various scenarios
4. Test API endpoints using tools like Postman or curl

### API Testing
- All endpoints support CORS for frontend integration
- Input validation is enforced on all POST/PUT requests
- Proper HTTP status codes are returned for different scenarios

## Future Enhancements

- User authentication and authorization
- Payment gateway integration
- Email notifications
- Seat selection interface
- Movie ratings and reviews
- Advanced search and filtering
- Booking history analytics
- Mobile application support

## Support

For any issues or questions, please refer to the project documentation or create an issue in the repository.

## License

This project is licensed under the MIT License.
