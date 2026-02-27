package com.example.roombook.DTO.booking;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class BookingResponse {
    private Long id;
    private Long officeId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String userId;
    private String note;

    public BookingResponse(Long id, Long officeId, LocalDateTime startTime, LocalDateTime endTime, String userId, String note) {
        this.id = id;
        this.officeId = officeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userId = userId;
        this.note = note;
    }

}
