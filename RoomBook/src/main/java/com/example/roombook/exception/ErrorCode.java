package com.example.roombook.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    OFFICE_NOT_FOUND("OFFICE_NOT_FOUND", "Office does not exist"),
    BOOKING_NOT_FOUND("BOOKING_NOT_FOUND", "Booking not found"),
    BOOKING_OVERLAP("BOOKING_OVERLAP", "Booking time overlaps with existing booking"),
    INVALID_BOOKING_TIME("INVALID_BOOKING_TIME", "Invalid booking time");

    private final String code;
    private final String defaultMessage;

    ErrorCode(String code, String defaultMessage) {
        this.code = code;
        this.defaultMessage = defaultMessage;
    }

}
