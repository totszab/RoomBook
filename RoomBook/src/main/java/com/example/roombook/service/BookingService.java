package com.example.roombook.service;

import com.example.roombook.entity.Booking;
import com.example.roombook.entity.Office;
import com.example.roombook.repository.BookingRepository;
import com.example.roombook.repository.OfficeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Booking createBooking(
            Long officeId,
            LocalDateTime startTime,
            LocalDateTime endTime,
            String bookedBy,
            String note
    ) {
        if (startTime.isAfter(endTime) || startTime.isEqual(endTime)) {
            throw new IllegalArgumentException("Invalid time interval");
        }

        Office office = officeRepository.findById(officeId)
                .orElseThrow(() -> new RuntimeException("Office not found"));

        boolean conflict = bookingRepository
                .existsByOfficeIdAndStartTimeLessThanAndEndTimeGreaterThan(
                        officeId,
                        endTime,
                        startTime
                );

        if (conflict) {
            throw new IllegalStateException("Office already booked in this time range");
        }

        Booking booking = new Booking();
        booking.setOffice(office);
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);
        booking.setBookedBy(bookedBy);
        booking.setNote(note);

        return bookingRepository.save(booking);
    }

    public List<Booking> getBookingsForOffice(Long officeId) {
        return bookingRepository.findByOfficeId(officeId);
    }
}
