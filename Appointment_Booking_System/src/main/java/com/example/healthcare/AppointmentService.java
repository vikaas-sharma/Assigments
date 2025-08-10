package com.example.healthcare;


import java.time.LocalDate;

public class AppointmentService {

    private DoctorRepository doctorRepository;

    // Setter-based Dependency Injection
    public void setDoctorRepository(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void book(String doctorId, LocalDate date) {
        if (doctorRepository.isAvailable(doctorId, date)) {
            System.out.println("Appointment confirmed");
        } else {
            System.out.println("Doctor not available");
        }
    }
}
