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

    // Inyección del repositorio de clientes
    @Autowired
    private ClientRepository repository;

    // Constructor para la inyección de dependencias
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
    public void deleteClient(Long id) {
        repository.deleteById(id);
    }

    // Obtener todas las ventas de un cliente
    public List<Sale> getSalesByClient(Long clientId) {
        Optional<Client> client = repository.findById(clientId);
        return client.map(Client::getSales).orElse(null);
    }
}

