package com.example.roombook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiError> handleBusiness(BusinessException ex) {
        ApiError error = new ApiError(
                ex.getErrorCode().getCode(),
                ex.getMessage()
        );

        return ResponseEntity
                .status(mapToHttpStatus(ex.getErrorCode()))
                .body(error);
    }

    private HttpStatus mapToHttpStatus(ErrorCode code) {
        return switch (code) {
            case OFFICE_NOT_FOUND, BOOKING_NOT_FOUND -> HttpStatus.NOT_FOUND;
            case BOOKING_OVERLAP, INVALID_BOOKING_TIME -> HttpStatus.BAD_REQUEST;
        };
    }
}
