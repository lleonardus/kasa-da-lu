package com.leonardus.kasadalu.controllers;

import com.leonardus.kasadalu.dtos.OrderDTO;
import com.leonardus.kasadalu.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kasa-da-lu/orders")
@Tag(name = "Order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    @Operation(summary = "List all orders")
    public ResponseEntity<List<OrderDTO>> findAll(){
        return ResponseEntity.ok().body(orderService.findAll());
    }
}
