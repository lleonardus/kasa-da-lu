package com.leonardus.kasadalu.controllers;

import com.leonardus.kasadalu.dtos.OrderDTO;
import com.leonardus.kasadalu.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kasa-da-lu/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll(){
        return ResponseEntity.ok().body(orderService.findAll());
    }
}
