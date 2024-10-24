package com.entrega2.example.controllers;

import com.entrega2.example.dto.ReceiptRequest;
import com.entrega2.example.entities.Receipt;
import com.entrega2.example.services.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    // Inyección de dependencias por constructor
    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping
    public ResponseEntity<Receipt> createReceipt(@Valid @RequestBody ReceiptRequest receiptRequest) {
        try {
            Receipt receipt = receiptService.createReceipt(receiptRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(receipt);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Receipt>> getReceiptsByDate(@PathVariable String date) {
        try {
            LocalDate localDate = LocalDate.parse(date);
            List<Receipt> receipts = receiptService.findReceiptsByDate(localDate);
            return ResponseEntity.status(HttpStatus.OK).body(receipts);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
