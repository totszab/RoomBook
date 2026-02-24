package com.example.roombook.service;

import com.example.roombook.DTO.OfficeRequest;
import com.example.roombook.entity.Office;
import com.example.roombook.repository.OfficeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfficeService {
    private final OfficeRepository officeRepository;

    public OfficeService(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    public List<Office> getAllOffices() {
        return officeRepository.findAll();
    }

    public Office getOfficeById(Long id) {
        return officeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Office not found"));
    }

    public Office createOffice(OfficeRequest request) {
        Office office = new Office();

        office.setName(request.getName());
        office.setCode(request.getCode());
        office.setCapacity(request.getCapacity());

        return officeRepository.save(office);
    }
}
