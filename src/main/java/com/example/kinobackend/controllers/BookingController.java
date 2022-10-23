package com.example.kinobackend.controllers;

import com.example.kinobackend.db_access.BookingSQL;
import com.example.kinobackend.db_access.SeatSQL;
import com.example.kinobackend.responses.BookingCreation;
import com.example.kinobackend.responses.SeatInEvent;
import com.example.kinobackend.responses.Ticket;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {
    @PostMapping("/api/newBooking")
    public boolean newBooking(@RequestBody BookingCreation bookingCreation){
        System.out.println("new booking creation:  customer=" + bookingCreation.getEmail() + " tickets=" + bookingCreation.getTicketIds().length);

        SeatSQL seatSQL = new SeatSQL();
        for(int id: bookingCreation.getTicketIds()){
            seatSQL.setSeatInEventStatus(new SeatInEvent("", 1, 1, id, 3));
        }

        return true;
    }

    @PostMapping("/api/ticketsInEvent")
    public Ticket[] getTicketsInEventId(@RequestBody int eventId){
        System.out.println("getting tickets for eventId: " + eventId);
        BookingSQL connector = new BookingSQL();


        Ticket[] tickets = connector.getTicketsForEventId(eventId);


        return tickets;
    }
}