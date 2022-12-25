package com.leonardus.kasadalu.controllers;

import com.leonardus.kasadalu.dtos.FrenchToastDTO;
import com.leonardus.kasadalu.services.FrenchToastService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(FrenchToastController.class)
class FrenchToastControllerTest {

    @MockBean
    FrenchToastService service;

    @Autowired
    MockMvc mockMvc;

    FrenchToastDTO frenchToastDTO;

    @BeforeEach
    void setUp() {
        frenchToastDTO = new FrenchToastDTO();
        when(service.findAll()).thenReturn(List.of(frenchToastDTO));
    }

    @Test
    @DisplayName("findAll returns 200")
    void findAll() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/kasa-da-lu/toasts").contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}