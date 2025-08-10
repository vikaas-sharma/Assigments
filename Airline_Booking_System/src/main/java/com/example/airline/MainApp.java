package com.example.airline;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        TicketService ticketService = (TicketService) context.getBean("ticketService");

        ticketService.confirmBooking("FL123", "USER456");
        ticketService.confirmBooking("FL456", "USER789");
    }
}
