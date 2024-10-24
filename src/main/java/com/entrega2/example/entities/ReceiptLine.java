package com.entrega2.example.entities;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Objects;

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

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // Este campo representa el producto de la línea del recibo

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private double lineTotal;

    // Sobrescribir equals y hashCode si es necesario
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptLine that = (ReceiptLine) o;
        return quantity == that.quantity && Double.compare(that.lineTotal, lineTotal) == 0 && Objects.equals(id, that.id) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product, quantity, lineTotal);
    }
}
