package com.example.roombook.controller;

import com.example.roombook.DTO.booking.BookingRequest;
import com.example.roombook.entity.Booking;
import com.example.roombook.entity.Office;
import com.example.roombook.repository.BookingRepository;
import com.example.roombook.repository.OfficeRepository;
import com.example.roombook.service.BookingService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class BookingControllerIT {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private OfficeRepository officeRepository;

    private Office testOffice;

    @BeforeEach
    void setup() {
        // create a test office for the new bookings
        testOffice = officeRepository.findByCode("TEST-OFFICE")
                .orElseGet(() -> {
                    Office o = new Office();
                    o.setName("Test Office");
                    o.setCode("TEST-OFFICE");
                    o.setCapacity(10);
                    return officeRepository.save(o);
                });
    }

    @Test
    void testBookingsWithOverlap() {
        // open time slot
        BookingRequest request1 = new BookingRequest();
        request1.setOfficeId(testOffice.getId());
        request1.setUserId("Test A");
        request1.setStartTime(LocalDateTime.of(2026, 3, 1, 9, 0));
        request1.setEndTime(LocalDateTime.of(2026, 3, 1, 11, 0));

        Booking booking1 = bookingService.createBooking(request1);
        assertNotNull(booking1.getId());

        // open time slot
        BookingRequest request2 = new BookingRequest();
        request2.setOfficeId(testOffice.getId());
        request2.setUserId("Test B");
        request2.setStartTime(LocalDateTime.of(2026, 3, 1, 12, 0));
        request2.setEndTime(LocalDateTime.of(2026, 3, 1, 13, 0));
        request2.setNote("Interview");

        Booking booking2 = bookingService.createBooking(request2);
        assertNotNull(booking2.getId());

        // overlapping
        BookingRequest overlapRequest = new BookingRequest();
        overlapRequest.setOfficeId(testOffice.getId());
        overlapRequest.setUserId("Test C");
        overlapRequest.setStartTime(LocalDateTime.of(2026, 3, 1, 10, 0));
        overlapRequest.setEndTime(LocalDateTime.of(2026, 3, 1, 12, 0));

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            bookingService.createBooking(overlapRequest);
        });

        assertTrue(exception.getMessage().contains("Office already booked"));

        // checking the count of created bookings
        List<Booking> bookings = bookingRepository.findByOfficeId(testOffice.getId());
        assertTrue(bookings.contains(booking1));
        assertTrue(bookings.contains(booking2));
        assertEquals(2, bookings.size());
    }
}
