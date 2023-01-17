package com.leonardus.kasadalu.services;

import com.leonardus.kasadalu.dtos.ClientDTO;
import com.leonardus.kasadalu.dtos.ClientInsertDTO;
import com.leonardus.kasadalu.entities.Client;
import com.leonardus.kasadalu.entities.FrenchToast;
import com.leonardus.kasadalu.entities.Order;
import com.leonardus.kasadalu.repositories.ClientRepository;
import com.leonardus.kasadalu.repositories.FrenchToastRepository;
import com.leonardus.kasadalu.services.exceptions.DataIntegrityViolationException;
import com.leonardus.kasadalu.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ClientServiceTest {
    @InjectMocks
    ClientService service;

    @Mock
    ClientRepository clientRepository;

    @Mock
    FrenchToastRepository toastRepository;

    @Mock
    ModelMapper mapper;

    Client client;
    ClientDTO clientDTO;
    ClientInsertDTO clientInsertDTO;
    FrenchToast frenchToast;

    @BeforeEach
    void setUp() {
        client = new Client(1L,"name", "lastname","4002-8922", new ArrayList<>());
        clientDTO = new ClientDTO(1L,"name", "lastname","4002-8922");
        clientInsertDTO = new ClientInsertDTO("name", "lastname","4002-8922");
        frenchToast = new FrenchToast();

        when(clientRepository.findAll()).thenReturn(List.of(client));
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(clientRepository.save(client)).thenReturn(client);
        when(toastRepository.findByFlavorIgnoreCase("chocolate")).thenReturn(Optional.of(frenchToast));

        when(mapper.map(client, ClientDTO.class)).thenReturn(clientDTO);
        when(mapper.map(clientDTO, Client.class)).thenReturn(client);
        when(mapper.map(clientInsertDTO, Client.class)).thenReturn(client);
    }

    @Test
    void findAll_ReturnsAListOfClientDTOs() {
        List<ClientDTO> response = service.findAll();

        assertNotNull(response);
        assertEquals(clientDTO, response.get(0));
    }

    @Test
    void findById_WhenSuccessful_ReturnsAClientDTO() {
        ClientDTO response = service.findById(1L);

        assertNotNull(response);
        assertEquals(clientDTO, response);
    }

    @Test
    void findById_WhenNotSuccessful_ThrowsAnObjectNotFoundException() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, ()-> service.findById(1L));
    }

    @Test
    void create_WhenSuccessful_ReturnsAClientDTO() {
        ClientDTO response = service.create(clientInsertDTO);

        assertNotNull(response);
        assertEquals(clientDTO, response);
    }

    @Test
    void update_WhenSuccessful_ReturnsAClientDTO() {
        clientDTO.setName("name updated");
        clientDTO.setLastname("lastname updated");
        clientDTO.setPhone("new phone");
        ClientDTO response = service.update(1L, clientInsertDTO);

        assertNotNull(response);
        assertEquals(clientDTO, response);
    }
    @Test
    void update_WhenNotSuccessful_ThrowsAnObjectNotFoundException() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, ()-> service.update(1L, clientInsertDTO));
    }

    @Test
    void buyToast_WhenSuccessful_ReturnsAnOrder() {
        Order response = service.buyToast(1L, "chocolate", 5);

        assertNotNull(response);
        assertEquals(new Order(null, frenchToast, 5, null), response);
    }

    @Test
    void buyToast_WhenUserNotFound_ThrowsAnObjectNotFoundException() {
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, ()-> service.buyToast(1L, "chocolate", 5));
    }

    @Test
    void buyToast_WhenToastNotFound_ThrowsAnObjectNotFoundException() {
        when(toastRepository.findByFlavorIgnoreCase("chocolate")).thenReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, ()-> service.buyToast(1L, "chocolate", 5));
    }

    @Test
    void buyToast_WhenToastQuantityIsLessThanOne_ThrowsDataIntegrityViolationException(){
        assertThrows(DataIntegrityViolationException.class, ()-> service.buyToast(1L, "chocolate", 0));
    }
}