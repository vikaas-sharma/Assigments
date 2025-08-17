package com.example.appointment_service.service;

import com.example.appointment_service.event.AppointmentConfirmedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Async  // make it non-blocking
    @EventListener
    public void handleAppointmentConfirmed(AppointmentConfirmedEvent event) {
        // ⚡ Instead of println you can call Email/SMS API
        System.out.println("📩 Sending notification: Appointment " + event.getAppointmentId() +
                " confirmed for patient " + event.getPatientId() +
                " with doctor " + event.getDoctorId());
    }
}
