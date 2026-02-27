package com.example.roombook.service;

import com.example.roombook.DTO.BookingRequest;
import com.example.roombook.entity.Booking;
import com.example.roombook.entity.Office;
import com.example.roombook.repository.BookingRepository;
import com.example.roombook.repository.OfficeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final OfficeRepository officeRepository;

    public BookingService(BookingRepository bookingRepository,
                          OfficeRepository officeRepository) {
        this.bookingRepository = bookingRepository;
        this.officeRepository = officeRepository;
    }

    public Booking createBooking(BookingRequest request) {
        if (request.getStartTime().isAfter(request.getEndTime()) || request.getStartTime().isEqual(request.getEndTime())) {
            throw new IllegalArgumentException("Invalid time interval");
        }

        Office office = officeRepository.findById(request.getOfficeId())
                .orElseThrow(() -> new RuntimeException("Office not found"));

        boolean isOverlapping = bookingRepository
                .existsByOfficeIdAndStartTimeLessThanAndEndTimeGreaterThan(
                        request.getOfficeId(),
                        request.getEndTime(),
                        request.getStartTime()
                );

        if (isOverlapping) {
            throw new IllegalStateException("Office already booked in this time range");
        }

        Booking booking = new Booking();
        booking.setOffice(office);
        booking.setStartTime(request.getStartTime());
        booking.setEndTime(request.getEndTime());
        booking.setUserId(request.getUserId());
        booking.setNote(request.getNote());

        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsForOffice(Long officeId) {
        return bookingRepository.findByOfficeId(officeId);
    }
}
