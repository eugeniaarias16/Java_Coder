package com.entrega2.example.dto;
import com.entrega2.example.repositories.ClientRepository;
import com.entrega2.example.dto.LineItemDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReceiptRequest {
    private ClientDTO client;
    private List<LineItemDTO> lineItems;

    // Si prefieres un método explícito
    public ClientDTO getClient() {
        return client;
    }

    public List<LineItemDTO> getLineItems() {
        return lineItems;
    }
}
