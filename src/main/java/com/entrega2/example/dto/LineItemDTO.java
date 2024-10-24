package com.entrega2.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LineItemDTO {
    private ProductDTO product;
    private int quantity;
}
