package com.example.roombook.DTO.office;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class OfficeRequest {
    @NotBlank
    private String code;

    @NotBlank
    private String name;

    @NotNull
    private Integer capacity;

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
