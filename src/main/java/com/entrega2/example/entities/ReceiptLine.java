package com.entrega2.example.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "receipt_lines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "line_total", nullable = false)
    private double lineTotal;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "receipt_id", nullable = false)
    private Receipt receipt;
}
