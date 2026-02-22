package com.example.roombook.controller;

import com.example.roombook.entity.Office;
import com.example.roombook.service.OfficeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offices")
public class OfficeController {
    private final OfficeService officeService;

    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping
    public List<Office> getAllOffices() {
        return officeService.getAllOffices();
    }

    @GetMapping("/{id}")
    public Office getOffice(@PathVariable Long id) {
        return officeService.getOfficeById(id);
    }

    @PostMapping
    public Office createOffice(@RequestBody Office office) {
        return officeService.createOffice(office);
    }
}
