package com.example.roombook.DTO;

import java.time.LocalDateTime;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
