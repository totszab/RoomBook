package com.example.roombook.converter;

import com.example.roombook.DTO.BookingResponse;
import com.example.roombook.entity.Booking;
import org.springframework.stereotype.Component;

@Component
public class BookingConverter {

    public BookingResponse getBookingResponse(Booking booking) {
        return new BookingResponse(
                booking.getId(),
                booking.getOffice().getId(),
                booking.getStartTime(),
                booking.getEndTime(),
                booking.getBookedBy(),
                booking.getNote()
        );
    }
}
