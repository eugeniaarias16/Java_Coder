package com.entrega2.example.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.entrega2.example.repositories.ClientRepository;
import com.entrega2.example.repositories.ProductRepository;
import com.entrega2.example.repositories.ReceiptRepository;
import com.entrega2.example.entities.Receipt;
import com.entrega2.example.dto.ReceiptRequest;
import com.entrega2.example.dto.ClientDTO; // Asegúrate de importar ClientDTO
import com.entrega2.example.entities.Client;
import com.entrega2.example.entities.Product;
import com.entrega2.example.entities.ReceiptLine;
import com.entrega2.example.dto.LineItemDTO;

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
        Client client = clientRepository.findById(receiptRequest.getClient().getId()) // Cambiado de getClientId() a getId()
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        double totalAmount = 0.0;

        // Crear las líneas del recibo
        List<ReceiptLine> receiptLines = new ArrayList<>();

        for (LineItemDTO lineItem : receiptRequest.getLineItems()) {
            // Validar que el producto exista
            Product product = productRepository.findById(lineItem.getProduct().getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));

            // Reducir el stock del producto usando el método decreaseStock
            product.decreaseStock(lineItem.getQuantity());
            productRepository.save(product);

            // Calcular el subtotal para este producto
            double lineTotal = lineItem.getQuantity() * product.getPrice();
            totalAmount += lineTotal;

            // Crear la línea del recibo
            ReceiptLine receiptLine = new ReceiptLine();
            receiptLine.setProduct(product);
            receiptLine.setQuantity(lineItem.getQuantity());
            receiptLine.setLineTotal(lineTotal);
            receiptLines.add(receiptLine);
        }

        // Crear el recibo usando el constructor parametrizado
        Receipt receipt = new Receipt(client, totalAmount, LocalDate.now(), receiptLines);

        // Guardar el recibo en la base de datos
        receiptRepository.save(receipt);

        return receipt;
    }

    // Buscar recibos por fecha
    public List<Receipt> findReceiptsByDate(LocalDate date) {
        List<Receipt> receipts = receiptRepository.findByDate(date);
        if (receipts.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron recibos para la fecha proporcionada");
        }
        return receipts;
    }

    // Gestionar devoluciones y aumentar el stock de los productos
    @Transactional
    public void returnReceipt(Long receiptId) {
        // Buscar el recibo
        Receipt receipt = receiptRepository.findById(receiptId)
                .orElseThrow(() -> new IllegalArgumentException("Recibo no encontrado"));

        // Aumentar el stock de cada producto en las líneas del recibo
        for (ReceiptLine line : receipt.getLines()) {
            Product product = line.getProduct();
            product.increaseStock(line.getQuantity()); // Incrementar el stock
            productRepository.save(product); // Guardar cambios
        }
    }
}
