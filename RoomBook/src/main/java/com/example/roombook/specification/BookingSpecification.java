package com.example.roombook.specification;

import com.example.roombook.DTO.booking.BookingRequest;
import com.example.roombook.entity.Booking;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BookingSpecification {
    public static Specification<Booking> bySearch(BookingRequest request) {

        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (request.getOfficeId() != null) {
                predicates.add(
                        cb.equal(root.get("office").get("id"), request.getOfficeId())
                );
            }

            if (request.getStartTime() != null) {
                predicates.add(
                        cb.greaterThanOrEqualTo(
                                root.get("startTime"),
                                request.getStartTime()
                        )
                );
            }

            if (request.getEndTime() != null) {
                predicates.add(
                        cb.lessThanOrEqualTo(
                                root.get("endTime"),
                                request.getEndTime()
                        )
                );
            }

            if (request.getUserId() != null && !request.getUserId().isBlank()) {
                predicates.add(
                        cb.equal(root.get("userId"), request.getUserId())
                );
            }

            if (request.getNote() != null && !request.getNote().isBlank()) {
                predicates.add(
                        cb.like(
                                cb.lower(root.get("note")),
                                "%" + request.getNote().toLowerCase() + "%"
                        )
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
