package com.entrega2.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.entrega2.example.entities.Product;
import com.entrega2.example.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    // Inyección del repositorio de productos
    @Autowired
    private ProductRepository repository;

    // Constructor para la inyección de dependencias
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    // Guardar un producto en la base de datos
    public Product save(Product product) {
        return repository.save(product);
    }

    // Obtener todos los productos
    public List<Product> getProducts() {
        return repository.findAll();
    }

    // Obtener un producto por su ID
    public Optional<Product> getById(Long id) {
        return repository.findById(id);
    }
}
