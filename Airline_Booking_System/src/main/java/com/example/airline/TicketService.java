package com.example.airline;

public class TicketService {
    private FlightService flightService;

    // Setter for Dependency Injection
    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    public void confirmBooking(String flightId, String userId) {
        if (flightService.isSeatAvailable(flightId)) {
            System.out.println("Booking confirmed for " + userId + " on " + flightId);
        } else {
            System.out.println("Flight " + flightId + " is full");
        }
    }
}
