package com.example.roombook.controller;

import com.example.roombook.DTO.office.OfficeRequest;
import com.example.roombook.DTO.office.OfficeResponse;
import com.example.roombook.entity.Office;
import com.example.roombook.service.OfficeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room-book/offices")
public class OfficeController {
    private final OfficeService officeService;

    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }

    @GetMapping
    public List<OfficeResponse> getAll() {
        return officeService.getAllOffices().stream()
                .map(o -> new OfficeResponse(
                        o.getId(),
                        o.getName(),
                        o.getCode(),
                        o.getCapacity()))
                .toList();
    }

    @GetMapping("/{id}")
    public OfficeResponse getOffice(@PathVariable Long id) {
        Office office = officeService.getOfficeById(id);
        return new OfficeResponse(
                office.getId(),
                office.getName(),
                office.getCode(),
                office.getCapacity()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OfficeResponse createOffice(@RequestBody OfficeRequest request) {

        Office office = officeService.createOffice(request);

        return new OfficeResponse(
                office.getId(),
                office.getName(),
                office.getCode(),
                office.getCapacity()
        );
    }
}
