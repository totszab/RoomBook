package com.example.roombook.controller;

import com.example.roombook.DTO.BookingRequest;
import com.example.roombook.entity.Booking;
import com.example.roombook.service.BookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room-book/bookings")
public class BookingController {
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking createBooking(@RequestBody BookingRequest request) {
        return bookingService.createBooking(
                request.getOfficeId(),
                request.getStartTime(),
                request.getEndTime(),
                request.getBookedBy(),
                request.getNote()
        );
    }

    @GetMapping("/office/{officeId}")
    public List<Booking> getBookingsForOffice(@PathVariable Long officeId) {
        return bookingService.getBookingsForOffice(officeId);
    }
}
