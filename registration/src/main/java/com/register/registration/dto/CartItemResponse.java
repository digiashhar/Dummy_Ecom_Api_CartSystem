package com.register.registration.dto;
import lombok.Data;
@Data
public class CartItemResponse {
    private Long cartItemId;
    private String productName;
    private int quantity;
    private double productCost ;
    public double getTotalPrice() {
        return quantity * productCost ;
    }
    public CartItemResponse(Long cartItemId, String productName, int quantity,  double productCost ) {
        this.cartItemId = cartItemId;
        this.productName = productName;
        this.quantity = quantity;
        this.productCost =productCost ;

    }

}