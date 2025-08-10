package com.example.healthcare;

import java.time.LocalDate;
import java.util.*;

public class DoctorRepository {
    private Map<String, List<LocalDate>> doctorSchedule = new HashMap<>();

    public DoctorRepository() {
        // Pre-filled dummy schedule
        doctorSchedule.put("DOC101", Arrays.asList(LocalDate.of(2025, 4, 10), LocalDate.of(2025, 4, 15)));
        doctorSchedule.put("DOC102", Arrays.asList(LocalDate.of(2025, 4, 11)));
    }

    public boolean isAvailable(String doctorId, LocalDate date) {
        List<LocalDate> availableDates = doctorSchedule.get(doctorId);
        return availableDates != null && availableDates.contains(date);
    }
}
