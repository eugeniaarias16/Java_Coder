package com.entrega2.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.entrega2.example.entities.Product;
import com.entrega2.example.services.ProductService;

import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    // Inyectamos la dependencia del servicio de productos
    @Autowired
    private ProductService service;

    // Constructor para la inyección de dependencias
    public ProductController(ProductService service) {
        this.service = service;
    }

    // Obtener todos los productos
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Iterable<Product>> getAll() {
        Iterable<Product> products = service.getProducts();
        return ResponseEntity.ok(products); // Devolver todos los productos
    }

    // Obtener un producto por ID
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Optional<Product>> getById(@PathVariable Long id) {
        Optional<Product> product = service.getById(id);

        if (product.isPresent()) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build(); // Producto no encontrado
        }
    }

    // Crear un nuevo producto
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> create(@RequestBody Product product) {
        try {
            Product newProduct = service.save(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

