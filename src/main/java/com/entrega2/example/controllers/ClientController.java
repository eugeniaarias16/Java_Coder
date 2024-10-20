package com.entrega2.example.controllers;

import com.entrega2.example.entities.Client;
import com.entrega2.example.entities.Product;
import com.entrega2.example.entities.Sale;
import com.entrega2.example.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Obtener todos los clientes
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    // Crear un nuevo cliente
    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    // Obtener un cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Optional<Client> client = clientService.getClientById(id);
        return client.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar un cliente por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    // Obtener todas las ventas de un cliente por su ID
    @GetMapping("/{id}/sales")
    public ResponseEntity<List<Sale>> getSalesByClient(@PathVariable Long id) {
        Optional<Client> client = clientService.getClientById(id);
        if (client.isPresent()) {
            return new ResponseEntity<>(client.get().getSales(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Obtener una venta específica de un producto realizado por un cliente
    @GetMapping("/{clientId}/purchases/{productId}")
    public ResponseEntity<Sale> getPurchaseOfProduct(@PathVariable Long clientId, @PathVariable Long productId) {
        Optional<Client> client = clientService.getClientById(clientId);
        if (client.isPresent()) {
            for (Sale sale : client.get().getSales()) {
                for (Product product : sale.getProducts()) {
                    if (product.getId().equals(productId)) {
                        return new ResponseEntity<>(sale, HttpStatus.OK);
                    }
                }
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
