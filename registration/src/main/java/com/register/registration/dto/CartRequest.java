package com.register.registration.dto;

import lombok.Data;

@Data
public class CartRequest {
    private Long productId;
    private int quantity;
}
