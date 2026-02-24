package com.example.roombook.controller;

import com.example.roombook.DTO.OfficeRequest;
import com.example.roombook.repository.OfficeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OfficeControllerIT {
    @Autowired
    OfficeRepository officeRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldReturnList() throws Exception {
        mockMvc.perform(get("/room-book/offices"))
                .andExpect(status().isOk());
    }

    @Test
    @Transactional
    void shouldCreateOffice() throws Exception {
        OfficeRequest request = new OfficeRequest();
        request.setName("HQ-123");
        request.setCode("123");
        request.setCapacity(123);

        mockMvc.perform(post("/room-book/offices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("HQ-123"));

        officeRepository.deleteByCode("123");
    }
}
