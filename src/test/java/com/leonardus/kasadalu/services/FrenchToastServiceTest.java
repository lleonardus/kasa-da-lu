package com.leonardus.kasadalu.services;

import com.leonardus.kasadalu.dtos.FrenchToastDTO;
import com.leonardus.kasadalu.entities.FrenchToast;
import com.leonardus.kasadalu.repositories.FrenchToastRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
class FrenchToastServiceTest {

    @InjectMocks
    FrenchToastService service;

    @Mock
    ModelMapper mapper;

    @Mock
    FrenchToastRepository repository;

    FrenchToast frenchToast;
    FrenchToastDTO frenchToastDTO;


    @BeforeEach
    void setUp(){
        frenchToast = new FrenchToast(1L, "Chocolate", 5.0);
        frenchToastDTO = new FrenchToastDTO(1L, "Chocolate", 5.0);

        Mockito.when(repository.findAll()).thenReturn(List.of(frenchToast));
        Mockito.when(mapper.map(frenchToast, FrenchToastDTO.class)).thenReturn(frenchToastDTO);
    }

    @Test
    void findAll() {
        List<FrenchToastDTO> response = service.findAll();

        assertNotNull(response);
        assertEquals(frenchToastDTO, response.get(0));
    }
}