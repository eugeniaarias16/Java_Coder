package com.entrega2.example.services;

import com.entrega2.example.entities.Client;
import com.entrega2.example.entities.Sale;
import com.entrega2.example.repositories.ClientRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository repository;

    @Autowired
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }


    // Obtener todos los clientes
    public List<Client> getAllClients() {
        return repository.findAll();
    }

    // Crear un nuevo cliente
    public Client createClient(Client client) {
        return repository.save(client);
    }

    // Obtener un cliente por su ID
    public Optional<Client> getClientById(Long id) {
        return repository.findById(id);
    }

    // Eliminar un cliente por ID
    public boolean deleteClient(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    // Obtener todas las ventas de un cliente
    public Optional<List<Sale>> getSalesByClient(Long clientId) {
        return repository.findById(clientId)
                .map(Client::getSales);
    }

    // Actualizar un cliente por ID
    public Optional<Client> updateClient(Long id, Client clientDetails) {
        return repository.findById(id).map(client -> {
            client.setFirstName(clientDetails.getFirstName());
            client.setLastName(clientDetails.getLastName());
            client.setDni(clientDetails.getDni());
            client.setBirthDate(clientDetails.getBirthDate());
            return repository.save(client);
        });
    }

    // Obtener una venta específica de un producto realizado por un cliente
    // Obtener una venta específica de un producto realizado por un cliente
    public Optional<Sale> getPurchaseOfProduct(Long clientId, Long productId) {
        return repository.findById(clientId)
                .map(client -> {
                    if (client.getSales() == null) {
                        return null; // No hay ventas
                    }
                    return client.getSales().stream()
                            .filter(sale -> sale.getProducts() != null && sale.getProducts().stream()
                                    .anyMatch(product -> product.getId().equals(productId)))
                            .findFirst()
                            .orElse(null);
                });
    }

}
