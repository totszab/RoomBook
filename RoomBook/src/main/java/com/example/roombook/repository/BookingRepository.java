package com.example.roombook.repository;

import com.example.roombook.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    boolean existsByOfficeIdAndStartTimeLessThanAndEndTimeGreaterThan(
            Long officeId,
            LocalDateTime end,
            LocalDateTime start
    );

    List<Booking> findByOfficeId(Long officeId);
}
