package com.leonardus.kasadalu.controllers;

import com.leonardus.kasadalu.controllers.exceptions.StandardError;
import com.leonardus.kasadalu.dtos.ClientDTO;
import com.leonardus.kasadalu.dtos.ClientInsertDTO;
import com.leonardus.kasadalu.dtos.OrderDTO;
import com.leonardus.kasadalu.entities.Order;
import com.leonardus.kasadalu.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/kasa-da-lu/clients")
@Tag(name = "Client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping
    @Operation(summary = "List all clients")
    public ResponseEntity<List<ClientDTO>> findAll(){
        return  ResponseEntity.ok().body(clientService.findAll());
    }

    @GetMapping("/{clientId}")
    @Operation(summary = "Get client by id",
    responses = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = StandardError.class)))
    })
    public ResponseEntity<ClientDTO> findById(@PathVariable Long clientId){
        return  ResponseEntity.ok().body(clientService.findById(clientId));
    }

    @PostMapping
    @Operation(summary = "Create client",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ClientDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = StandardError.class)))
            })
    public ResponseEntity<ClientDTO> create(@RequestBody ClientInsertDTO clientInsertDTO){
        ClientDTO client = clientService.create(clientInsertDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(client.getId()).toUri();

        return ResponseEntity.created(uri).body(client);
    }

    @PutMapping("/{clientId}")
    @Operation(summary = "Update client",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ClientDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = StandardError.class))),
                    @ApiResponse(responseCode = "404", description = "Not found",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = StandardError.class)))
            })
    public ResponseEntity<ClientDTO> update(@PathVariable Long clientId, @RequestBody ClientInsertDTO clientInsertDTO){
        ClientDTO client = clientService.update(clientId, clientInsertDTO);
        return ResponseEntity.ok().body(client);
    }

    @PostMapping("/{clientId}/buy")
    @Operation(summary = "Update client",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = OrderDTO.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = StandardError.class))),
                    @ApiResponse(responseCode = "404", description = "Not found",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = StandardError.class)))
            })
    public ResponseEntity<Order> buyToast(@PathVariable Long clientId, @RequestParam String flavor, @RequestParam Integer quantity){
        return ResponseEntity.ok().body(clientService.buyToast(clientId, flavor, quantity));
    }
}
