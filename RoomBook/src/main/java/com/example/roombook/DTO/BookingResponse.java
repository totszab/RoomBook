package com.example.roombook.DTO;

import java.time.LocalDateTime;

public class BookingResponse {
    private Long id;
    private Long officeId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String bookedBy;
    private String note;

    public BookingResponse(Long id, Long officeId, LocalDateTime startTime, LocalDateTime endTime, String bookedBy, String note) {
        this.id = id;
        this.officeId = officeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bookedBy = bookedBy;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Long officeId) {
        this.officeId = officeId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(String bookedBy) {
        this.bookedBy = bookedBy;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
