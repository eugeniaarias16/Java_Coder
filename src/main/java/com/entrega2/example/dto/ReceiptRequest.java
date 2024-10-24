package com.entrega2.example.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class ReceiptRequest {
    private ClientDTO client;
    private List<LineItemDTO> lineItems;
}

