package com.example.roombook.DTO.office;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OfficeResponse {
    private Long id;
    private String name;
    private String code;
    private Integer capacity;

    public OfficeResponse(Long id, String name, String code, Integer capacity) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.capacity = capacity;
    }

}
