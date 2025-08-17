package com.example.doctor_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctor_schedule")
public class DoctorSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public String getTimeSlot() {
		return timeSlot;
	}

	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}

	public boolean isBooked() {
		return booked;
	}

	public void setBooked(boolean booked) {
		this.booked = booked;
	}

	private Long doctorId;        // Is doctor a slot ha
    private String timeSlot;      // e.g. 2025-08-17T10:00
    private boolean booked;       // true = booked, false = available

    public DoctorSchedule() {}

    public DoctorSchedule(Long doctorId, String timeSlot, boolean booked) {
        this.doctorId = doctorId;
        this.timeSlot = timeSlot;
        this.booked = booked;
    }

    // getters/setters
}

