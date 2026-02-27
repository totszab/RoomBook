package com.example.roombook.controller;

import com.example.roombook.DTO.booking.BookingRequest;
import com.example.roombook.DTO.booking.BookingResponse;
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

    @PostMapping("/create")
    public BookingResponse createBooking(@RequestBody BookingRequest request) {
        return bookingService.createBooking(request);
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
