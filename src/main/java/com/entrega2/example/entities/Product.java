package com.entrega2.example.entities;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "sales") // Evitar posibles ciclos infinitos al imprimir
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private int stock;
    private double price;
    private String colors;
    private String sizes;

    @ManyToMany(mappedBy = "products", cascade = CascadeType.MERGE)
    private List<Sale> sales = new ArrayList<>();

    // Métodos adicionales para manejar el stock
    public void increaseStock(int amount) {
        this.stock += amount;
    }

    public void decreaseStock(int amount) {
        if (amount > this.stock) {
            throw new IllegalArgumentException("Stock insuficiente");
        }
        this.stock -= amount;
    }

    public String getName() {
        return description; // Si `name` es el mismo que `description`
    }
}
