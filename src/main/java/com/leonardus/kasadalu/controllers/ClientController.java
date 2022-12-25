package com.leonardus.kasadalu.controllers;

import com.leonardus.kasadalu.dtos.ClientDTO;
import com.leonardus.kasadalu.entities.Order;
import com.leonardus.kasadalu.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/kasa-da-lu/clients")
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAll(){
        return  ResponseEntity.ok().body(clientService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> findById(@PathVariable Long id){
        return  ResponseEntity.ok().body(clientService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO clientDTO){
        ClientDTO client = clientService.create(clientDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(uri).body(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody ClientDTO clientDTO){
        ClientDTO client = clientService.update(id, clientDTO);
        return ResponseEntity.ok().body(client);
    }

    @PostMapping("/{id}/buy")
    public ResponseEntity<Order> buyToast(@PathVariable Long id, @RequestParam String flavor, @RequestParam Integer quantity){
        return ResponseEntity.ok().body(clientService.buyToast(id, flavor, quantity));
    }
}
