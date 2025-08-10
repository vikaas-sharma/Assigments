package com.example.healthcare;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.time.LocalDate;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        AppointmentService appointmentService = (AppointmentService) context.getBean("appointmentService");

        appointmentService.book("DOC101", LocalDate.of(2025, 4, 10));  // Should print: Appointment confirmed
        appointmentService.book("DOC101", LocalDate.of(2025, 4, 12));  // Should print: Doctor not available
    }
}
