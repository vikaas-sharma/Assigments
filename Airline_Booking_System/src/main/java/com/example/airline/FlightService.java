package com.example.airline;

import java.util.*;

public class FlightService {
    private Map<String, Integer> flightSeats = new HashMap<>();

    public FlightService() {
        // Sample flights and seat count
        flightSeats.put("FL123", 3);
        flightSeats.put("FL456", 0); // Full
    }

    public boolean isSeatAvailable(String flightId) {
        Integer seats = flightSeats.get(flightId);
        return seats != null && seats > 0;
    }
}
