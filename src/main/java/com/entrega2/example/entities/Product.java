package com.entrega2.example.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private int stock;
    private double price;
    private String colors;
    private String sizes;

    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private List<Sale> sales = new ArrayList<>();

    // Default constructor
    public Product() {}

    // Constructor with parameters
    public Product(String description, int stock, double price, String colors, String sizes) {
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.colors = colors;
        this.sizes = sizes;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColors() {
        return colors;
    }

    public void setColors(String colors) {
        this.colors = colors;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                ", colors='" + colors + '\'' +
                ", sizes='" + sizes + '\'' +
                '}';
    }
}
