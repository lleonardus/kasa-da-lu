package com.leonardus.kasadalu.services;

import com.leonardus.kasadalu.dtos.OrderDTO;
import com.leonardus.kasadalu.repositories.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    ModelMapper mapper;
    @Autowired
    OrderRepository orderRepository;

    public List<OrderDTO> findAll(){
        return orderRepository.findAll().stream()
                .map(order -> mapper.map(order, OrderDTO.class))
                .toList();
    }
}
