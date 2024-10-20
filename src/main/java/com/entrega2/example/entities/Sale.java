package com.entrega2.example.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;
    private double total;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany
    @JoinTable(
            name = "sale_product",
            joinColumns = @JoinColumn(name = "sale_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    // Default constructor
    public Sale() {}

    // Constructor with parameters
    public Sale(Client client, List<Product> products, int quantity, double total, LocalDate date) {
        this.client = client;
        this.products = products;
        this.quantity = quantity;
        this.total = total;
        this.date = date;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder productsStr = new StringBuilder();
        for (Product product : products) {
            productsStr.append(product.getDescription()).append(", ");
        }

        return "Sale{" +
                "id=" + id +
                ", client=" + client.getFirstName() +
                ", products=" + productsStr.toString() +
                ", quantity=" + quantity +
                ", total=" + total +
                ", date=" + date +
                '}';
    }
}
