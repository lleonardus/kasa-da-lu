package com.leonardus.kasadalu.controllers;

import com.leonardus.kasadalu.dtos.FrenchToastDTO;
import com.leonardus.kasadalu.services.FrenchToastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kasa-da-lu/toasts")
public class FrenchToastController {
    @Autowired
    FrenchToastService toastService;

    @GetMapping
    public ResponseEntity<List<FrenchToastDTO>> findAll(){
        return ResponseEntity.ok().body(toastService.findAll());
    }
}
