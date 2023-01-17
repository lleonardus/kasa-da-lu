package com.leonardus.kasadalu.controllers;

import com.leonardus.kasadalu.dtos.FrenchToastDTO;
import com.leonardus.kasadalu.services.FrenchToastService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kasa-da-lu/toasts")
@Tag(name = "French Toast")
public class FrenchToastController {
    @Autowired
    FrenchToastService toastService;

    @GetMapping
    @Operation(summary = "List all french toast options")
    public ResponseEntity<List<FrenchToastDTO>> findAll(){
        return ResponseEntity.ok().body(toastService.findAll());
    }
}
