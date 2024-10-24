package com.entrega2.example.repositories;

import com.entrega2.example.entities.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {

    // Método para buscar recibos por una fecha específica
    List<Receipt> findByDate(LocalDate date);
}
