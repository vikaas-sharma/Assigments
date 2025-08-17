package com.example.appointment_service.event;

public class AppointmentConfirmedEvent {
    private Long appointmentId;
    private Long patientId;
    private Long doctorId;

    public AppointmentConfirmedEvent(Long appointmentId, Long patientId, Long doctorId) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
    }

    public Long getAppointmentId() { return appointmentId; }
    public Long getPatientId() { return patientId; }
    public Long getDoctorId() { return doctorId; }
}