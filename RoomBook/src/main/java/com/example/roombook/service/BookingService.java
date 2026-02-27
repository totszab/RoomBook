package com.example.roombook.service;

import com.example.roombook.DTO.booking.BookingRequest;
import com.example.roombook.DTO.booking.BookingResponse;
import com.example.roombook.entity.Booking;
import com.example.roombook.entity.Office;
import com.example.roombook.exception.BusinessException;
import com.example.roombook.exception.ErrorCode;
import com.example.roombook.repository.BookingRepository;
import com.example.roombook.repository.OfficeRepository;
import com.example.roombook.specification.BookingSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            throw new BusinessException(ErrorCode.INVALID_BOOKING_TIME);
        }

        Office office = officeRepository.findById(request.getOfficeId())
                .orElseThrow(() -> new BusinessException(ErrorCode.OFFICE_NOT_FOUND));

        boolean isOverlapping = bookingRepository
                .existsByOfficeIdAndStartTimeLessThanAndEndTimeGreaterThan(
                        request.getOfficeId(),
                        request.getEndTime(),
                        request.getStartTime()
                );

        if (isOverlapping) {
            throw new BusinessException(ErrorCode.BOOKING_OVERLAP);
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
        if (!officeRepository.existsById(officeId)) {
            throw new BusinessException(ErrorCode.OFFICE_NOT_FOUND);
        }
        return bookingRepository.findByOfficeId(officeId);
    }

    public BookingResponse getById(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new BusinessException(ErrorCode.BOOKING_NOT_FOUND)
                );

        return new BookingResponse(booking.getId(),
                booking.getOffice().getId(),
                booking.getStartTime(),
                booking.getEndTime(),
                booking.getUserId(),
                booking.getNote());
    }

    public List<BookingResponse> search(BookingRequest request) {

        Specification<Booking> specification =
                BookingSpecification.bySearch(request);

        List<Booking> bookings =
                bookingRepository.findAll(specification);

        List<BookingResponse> responses = new ArrayList<>();

        for (Booking booking : bookings) {
            BookingResponse response = new BookingResponse(booking.getId(),
                    booking.getOffice().getId(),
                    booking.getStartTime(),
                    booking.getEndTime(),
                    booking.getUserId(),
                    booking.getNote());

            responses.add(response);
        }

        return responses;
    }

    public void deleteBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElseThrow(() -> new BusinessException(ErrorCode.BOOKING_NOT_FOUND));
        bookingRepository.delete(booking);
    }
}
