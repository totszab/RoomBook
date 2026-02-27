package com.example.roombook.controller;

import com.example.roombook.DTO.BookingRequest;
import com.example.roombook.DTO.BookingResponse;
import com.example.roombook.converter.BookingConverter;
import com.example.roombook.entity.Booking;
import com.example.roombook.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room-book/bookings")
public class BookingController {
    @Autowired
    private BookingConverter bookingConverter;
    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public BookingResponse createBooking(@RequestBody BookingRequest request) {
        Booking booking = bookingService.createBooking(request);
        return bookingConverter.getBookingResponse(booking);
    }

    @GetMapping("/office/{officeId}")
    public List<BookingResponse> getBookingsForOffice(@PathVariable Long officeId) {
        return bookingService.getBookingsForOffice(officeId).stream().map(o -> new BookingResponse(
                o.getId(),
                o.getOffice().getId(),
                o.getStartTime(),
                o.getEndTime(),
                o.getUserId(),
                o.getNote()
        )).toList();
    }
}
