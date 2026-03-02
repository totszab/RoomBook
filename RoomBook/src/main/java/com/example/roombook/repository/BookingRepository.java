package com.example.roombook.repository;

import com.example.roombook.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> , JpaSpecificationExecutor<Booking> {

    boolean existsByOfficeIdAndStartTimeLessThanAndEndTimeGreaterThan(
            Long officeId,
            LocalDateTime end,
            LocalDateTime start
    );

    List<Booking> findByOfficeId(Long officeId);
}
