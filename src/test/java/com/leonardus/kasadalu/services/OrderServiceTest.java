package com.leonardus.kasadalu.services;

import com.leonardus.kasadalu.dtos.FrenchToastDTO;
import com.leonardus.kasadalu.dtos.OrderDTO;
import com.leonardus.kasadalu.entities.FrenchToast;
import com.leonardus.kasadalu.entities.Order;
import com.leonardus.kasadalu.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
class OrderServiceTest {
    @InjectMocks
    OrderService service;

    @Mock
    ModelMapper mapper;

    @Mock
    OrderRepository repository;

    Order order;
    OrderDTO orderDTO;

    @BeforeEach
    void setUp() {
        order = new Order(1L, new FrenchToast(1L, "chocolate", 5.0), 5, new Date());
        orderDTO = new OrderDTO(1L, new FrenchToastDTO(1L, "chocolate", 5.0), 5, new Date());

        Mockito.when(repository.findAll()).thenReturn(List.of(order));
        Mockito.when(mapper.map(order, OrderDTO.class)).thenReturn(orderDTO);
    }

    @Test
    void findAll() {
        List<OrderDTO> response = service.findAll();
        assertNotNull(response);
        assertEquals(orderDTO, response.get(0));
    }
}