package com.entrega2.example.services;

import com.entrega2.example.dto.ClientDTO;
import com.entrega2.example.dto.LineItemDTO;
import com.entrega2.example.dto.ReceiptRequest;
import com.entrega2.example.entities.Client;
import com.entrega2.example.entities.Product;
import com.entrega2.example.entities.Receipt;
import com.entrega2.example.entities.ReceiptLine;
import com.entrega2.example.repositories.ClientRepository;


import com.entrega2.example.repositories.ProductRepository;
import com.entrega2.example.repositories.ReceiptRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReceiptService {

    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final ReceiptRepository receiptRepository;

    // Constructor para inyección de dependencias
    public ReceiptService(ClientRepository clientRepository, ProductRepository productRepository, ReceiptRepository receiptRepository) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.receiptRepository = receiptRepository;
    }

    @Transactional
    public Receipt createReceipt(ReceiptRequest receiptRequest) {
        // Validar que el cliente exista
        Client client = clientRepository.findById(receiptRequest.getClient().getId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        // Validar que la lista de productos no esté vacía
        if (receiptRequest.getLineItems().isEmpty()) {
            throw new IllegalArgumentException("La lista de productos no puede estar vacía");
        }

        double totalAmount = 0.0;
        List<ReceiptLine> receiptLines = new ArrayList<>();

        // Crear las líneas del recibo
        for (LineItemDTO lineItem : receiptRequest.getLineItems()) {
            Product product = productRepository.findById(lineItem.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));


            // Verificar si hay suficiente stock del producto
            if (product.getStock() < lineItem.getQuantity()) {
                throw new IllegalArgumentException("No hay suficiente stock para el producto: " + product.getName());
            }

            // Reducir el stock del producto
            product.decreaseStock(lineItem.getQuantity());
            totalAmount += lineItem.getQuantity() * product.getPrice();

            // Crear la línea del recibo
            ReceiptLine receiptLine = new ReceiptLine();
            receiptLine.setProduct(product);
            receiptLine.setQuantity(lineItem.getQuantity());
            receiptLine.setLineTotal(lineItem.getQuantity() * product.getPrice());
            receiptLines.add(receiptLine);
        }

        // Crear y guardar el recibo
        Receipt receipt = new Receipt(client, totalAmount, LocalDate.now(), receiptLines);
        Receipt savedReceipt = receiptRepository.save(receipt);

        // Guardar los productos con el stock actualizado
        productRepository.saveAll(receiptLines.stream().map(ReceiptLine::getProduct).toList());

        return savedReceipt;
    }

    // Buscar recibos por fecha
    public List<Receipt> findReceiptsByDate(LocalDate date) {
        return receiptRepository.findByDate(date);
    }

    // Gestionar devoluciones y aumentar el stock de los productos
    @Transactional
    public void returnReceipt(Long receiptId) {
        // Buscar el recibo
        Receipt receipt = receiptRepository.findById(receiptId)
                .orElseThrow(() -> new IllegalArgumentException("Recibo no encontrado"));

        // Aumentar el stock de cada producto en las líneas del recibo
        for (ReceiptLine line : receipt.getLines()) {
            line.getProduct().increaseStock(line.getQuantity());
        }

        // Guardar los productos actualizados
        productRepository.saveAll(receipt.getLines().stream().map(ReceiptLine::getProduct).toList());
    }
}
