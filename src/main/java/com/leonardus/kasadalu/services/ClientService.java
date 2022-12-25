package com.leonardus.kasadalu.services;

import com.leonardus.kasadalu.dtos.ClientDTO;
import com.leonardus.kasadalu.entities.Client;
import com.leonardus.kasadalu.entities.FrenchToast;
import com.leonardus.kasadalu.entities.Order;
import com.leonardus.kasadalu.repositories.ClientRepository;
import com.leonardus.kasadalu.repositories.FrenchToastRepository;
import com.leonardus.kasadalu.services.exceptions.DataIntegrityViolationException;
import com.leonardus.kasadalu.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    ModelMapper mapper;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    FrenchToastRepository toastRepository;

    public List<ClientDTO> findAll(){
        return clientRepository.findAll().stream()
                .map(client -> mapper.map(client, ClientDTO.class))
                .toList();
    }

    public ClientDTO findById(Long id){
        Client client = this.findByIdOrElseThrowObjectNotFoundException(id);

        return mapper.map(client, ClientDTO.class);
    }

    public ClientDTO create(ClientDTO clientDTO){
        Client client = mapper.map(clientDTO, Client.class);
        Client createdClient = clientRepository.save(client);
        clientDTO.setId(createdClient.getId());
        return clientDTO;
    }

    public ClientDTO update(Long id, ClientDTO clientDTO){
        Client client = this.findByIdOrElseThrowObjectNotFoundException(id);
        client.setName(clientDTO.getName());
        client.setLastname(clientDTO.getLastname());
        client.setPhone(clientDTO.getPhone());

        return mapper.map(clientRepository.save(client), ClientDTO.class);
    }

    public Order buyToast(Long clientId, String toastFlavor, Integer toastQuantity) {
        isToastQuantityGreaterThanZero(toastQuantity);
        Client client = this.findByIdOrElseThrowObjectNotFoundException(clientId);

        FrenchToast toast = toastRepository.findByFlavorIgnoreCase(toastFlavor)
                .orElseThrow(() -> new ObjectNotFoundException("Flavor not found"));

        Order order = Order.builder()
                .quantity(toastQuantity)
                .frenchToast(toast)
                .build();

        client.getOrders().add(order);

        clientRepository.save(client);

        return client.getOrders().get(client.getOrders().size() - 1);
    }

    private Client findByIdOrElseThrowObjectNotFoundException(Long id){
        return clientRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Client not found"));
    }

    private void isToastQuantityGreaterThanZero(Integer toastQuantity){
        if (toastQuantity < 1) throw new DataIntegrityViolationException("You must order at least 1 french toast");
    }
}
