package com.leonardus.kasadalu.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leonardus.kasadalu.dtos.ClientDTO;
import com.leonardus.kasadalu.dtos.ClientInsertDTO;
import com.leonardus.kasadalu.entities.Order;
import com.leonardus.kasadalu.services.ClientService;
import com.leonardus.kasadalu.services.exceptions.DataIntegrityViolationException;
import com.leonardus.kasadalu.services.exceptions.ObjectNotFoundException;
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

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @MockBean
    ClientService service;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    ClientDTO clientDTO;
    ClientInsertDTO clientInsertDTO;
    Order order;

    @BeforeEach
    void setUp() {
        clientDTO = new ClientDTO();
        clientInsertDTO = new ClientInsertDTO();
        order = new Order();

        when(service.findAll()).thenReturn(List.of(clientDTO));
        when(service.findById(1L)).thenReturn(clientDTO);
        when(service.create(clientInsertDTO)).thenReturn(clientDTO);
        when(service.update(1L, clientInsertDTO)).thenReturn(clientDTO);
        when(service.buyToast(1L, "chocolate", 5)).thenReturn(order);
    }

    @Test
    @DisplayName("findAll returns 200")
    void findAll() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/kasa-da-lu/clients").contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("findById returns 200 when client exists")
    void findById_WhenSuccessful_ReturnsAClientDTO() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/kasa-da-lu/clients/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("findById returns 400 when client does not exist")
    void findById_WhenNotSuccessful_ThrowsAnObjectNotFoundException() throws Exception{
        when(service.findById(1L)).thenThrow(ObjectNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/kasa-da-lu/clients/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @DisplayName("create returns 201")
    void create_WhenSuccessful_ReturnsAClientDTO() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/kasa-da-lu/clients")
                        .content(objectMapper.writeValueAsString(clientInsertDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.header().exists("Location"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void update_WhenSuccessful_ReturnsAClientDTO() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.put("/kasa-da-lu/clients/{id}", 1L)
                        .content(objectMapper.writeValueAsString(clientInsertDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void update_WhenNotSuccessful_ThrowsAnObjectNotFoundException() throws Exception{
        when(service.update(1L, clientInsertDTO)).thenThrow(ObjectNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.put("/kasa-da-lu/clients/{id}", 1L)
                        .content(objectMapper.writeValueAsString(clientInsertDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void buyToast_WhenSuccessful_ReturnsAnOrder() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/kasa-da-lu/clients/{id}/buy?flavor={flavor}&quantity={quantity}",
                                1L, "chocolate", 5)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void buyToast_WhenUserNotFound_ThrowsAnObjectNotFoundException() throws Exception{
        when(service.buyToast(1L, "chocolate", 5)).thenThrow(ObjectNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/kasa-da-lu/clients/{id}/buy?flavor={flavor}&quantity={quantity}",
                                1L, "chocolate", 5)
                        .content(objectMapper.writeValueAsString(clientDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void buyToast_WhenToastNotFound_ThrowsAnObjectNotFoundException() throws Exception{
        when(service.buyToast(1L, "chocolate", 5)).thenThrow(ObjectNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/kasa-da-lu/clients/{id}/buy?flavor={flavor}&quantity={quantity}",
                                1L, "chocolate", 5)
                        .content(objectMapper.writeValueAsString(clientDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void buyToast_WhenToastQuantityIsLessThanOne_ThrowsDataIntegrityViolationException() throws Exception{
        when(service.buyToast(1L, "chocolate", 0)).thenThrow(DataIntegrityViolationException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/kasa-da-lu/clients/{id}/buy?flavor={flavor}&quantity={quantity}",
                                1L, "chocolate", 0)
                        .content(objectMapper.writeValueAsString(clientDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}