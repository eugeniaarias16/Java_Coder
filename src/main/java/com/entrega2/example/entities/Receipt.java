package com.entrega2.example.entities;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "receipts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    private double totalAmount;

    private LocalDate date;

    @Getter
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "receipt_id")
    private List<ReceiptLine> lines;

    // Constructor parametrizado
    public Receipt(Client client, double totalAmount, LocalDate date, List<ReceiptLine> lines) {
        this.client = client;
        this.totalAmount = totalAmount;
        this.date = date;
        this.lines = lines;
    }

}
