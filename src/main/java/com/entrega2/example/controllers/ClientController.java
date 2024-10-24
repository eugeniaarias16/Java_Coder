package com.entrega2.example.controllers;

import com.entrega2.example.dto.ClientDTO;
import com.entrega2.example.entities.Client;
import com.entrega2.example.entities.Sale;
import com.entrega2.example.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO clientDTO) {
        Client client = clientDTO.toEntity();
        Client savedClient = clientService.createClient(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ClientDTO(savedClient));
    }

    // Obtener todos los clientes
    @GetMapping
    public ResponseEntity<?> getAllClients() {
        try {
            List<ClientDTO> clients = clientService.getAllClients().stream()
                    .map(ClientDTO::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(clients);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener los clientes: " + e.getMessage());
        }
    }

    // Obtener un cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {
        return clientService.getClientById(id)
                .map(client -> ResponseEntity.ok(new ClientDTO(client)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar un cliente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        if (clientService.deleteClient(id)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
        }
    }

    // Obtener todas las ventas de un cliente por su ID
    @GetMapping("/{id}/sales")
    public ResponseEntity<List<Sale>> getSalesByClient(@PathVariable Long id) {
        return clientService.getSalesByClient(id)
                .map(sales -> new ResponseEntity<>(sales, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar un cliente por ID
    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO) {
        Client clientDetails = clientDTO.toEntity();
        return clientService.updateClient(id, clientDetails)
                .map(updatedClient -> ResponseEntity.ok(new ClientDTO(updatedClient)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Obtener una venta específica de un producto realizado por un cliente
    @GetMapping("/{clientId}/purchases/{productId}")
    public ResponseEntity<Sale> getPurchaseOfProduct(@PathVariable Long clientId, @PathVariable Long productId) {
        return clientService.getPurchaseOfProduct(clientId, productId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
