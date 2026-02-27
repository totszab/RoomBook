package com.example.roombook.DTO.booking;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class BookingRequest {
    private Long officeId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String userId;
    private String note;

}
