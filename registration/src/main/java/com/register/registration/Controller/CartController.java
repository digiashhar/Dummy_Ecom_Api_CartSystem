package com.register.registration.Controller;

import com.register.registration.Service.ServiceImpl.CartService;
import com.register.registration.dto.CartItemResponse;
import com.register.registration.dto.CartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    // Endpoint to add a product to the cart
    @PostMapping("/user={userId}/add")
    public ResponseEntity<String> addProductToCart(@PathVariable Long userId, @RequestBody CartRequest cartRequest) {
        boolean added = cartService.addProductToCart(userId, cartRequest);
        if (added) {
            return ResponseEntity.ok("Product added to cart successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found");
        }
    }

    // Endpoint to retrieve cart items for a user
    @GetMapping("/user={userId}")
    public ResponseEntity<List<CartItemResponse>> getCartItems(@PathVariable Long userId) {
        List<CartItemResponse> cartItems = cartService.getCartItems(userId);
        return ResponseEntity.ok(cartItems);
    }

    // Endpoint to update the quantity of a product in the cart
    @PutMapping("/user={userId}/update")
    public ResponseEntity<String> updateCartItemQuantity(@PathVariable Long userId, @RequestBody CartRequest cartRequest) {
        boolean updated = cartService.updateCartItemQuantity(userId, cartRequest);
        if (updated) {
            return ResponseEntity.ok("Cart item quantity updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or cart item not found");
        }
    }
}
