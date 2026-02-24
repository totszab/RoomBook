package com.example.roombook.repository;

import com.example.roombook.entity.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends JpaRepository<Office, Long> {
    void deleteByCode(String code);
}
