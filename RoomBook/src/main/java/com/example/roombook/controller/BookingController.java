package com.example.roombook.controller;

import com.example.roombook.DTO.booking.BookingRequest;
import com.example.roombook.DTO.booking.BookingResponse;
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

    @GetMapping("/{bookingId}")
    public BookingResponse getById(@PathVariable Long bookingId) {
        return bookingService.getById(bookingId);
    }

    @PostMapping("/search")
    public List<BookingResponse> search(@RequestBody BookingRequest request) {
        return bookingService.search(request);
    }

    @DeleteMapping("/{bookingId}")
    public void deleteBooking(@PathVariable Long bookingId) {
        bookingService.deleteBooking(bookingId);
    }
}
