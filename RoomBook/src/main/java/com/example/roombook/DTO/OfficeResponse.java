package com.example.roombook.DTO;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
