package com.example.doctor_service.service;

import com.example.doctor_service.model.Doctor;
import com.example.doctor_service.model.DoctorSchedule;
import com.example.doctor_service.repository.DoctorRepository;
import com.example.doctor_service.repository.DoctorScheduleRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorScheduleRepository scheduleRepository;

    // ✅ Only ONE constructor
    public DoctorService(DoctorRepository doctorRepository,
                         DoctorScheduleRepository scheduleRepository) {
        this.doctorRepository = doctorRepository;
        this.scheduleRepository = scheduleRepository;
    }

    // 🔹 Doctor CRUD Methods
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }

    public List<Doctor> getDoctorsBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization);
    }

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    // 🔹 Slot Management
    public boolean blockSlot(Long doctorId, String timeSlot) {
        Optional<DoctorSchedule> existingSlot = scheduleRepository.findByDoctorIdAndTimeSlot(doctorId, timeSlot);

        if (existingSlot.isPresent()) {
            DoctorSchedule schedule = existingSlot.get();
            if (schedule.isBooked()) {
                return false; // already booked
            } else {
                schedule.setBooked(true);
                scheduleRepository.save(schedule);
                return true;
            }
        } else {
            DoctorSchedule newSlot = new DoctorSchedule(doctorId, timeSlot, true);
            scheduleRepository.save(newSlot);
            return true;
        }
    }
}
