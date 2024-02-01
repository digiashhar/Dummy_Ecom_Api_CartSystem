package com.register.registration.Service.ServiceImpl;

import com.register.registration.dto.CartItemResponse;
import com.register.registration.dto.CartRequest;
import com.register.registration.entity.Cart;
import com.register.registration.entity.Product;
import com.register.registration.entity.User;
import com.register.registration.repository.CartRepository;
import com.register.registration.repository.ProductRepository;
import com.register.registration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public boolean addProductToCart(Long userId, CartRequest cartRequest) {
        User user = userRepository.findById(userId).orElse(null);
        Product product = productRepository.findById(cartRequest.getProductId()).orElse(null);

        if (user != null && product != null) {
            Cart existingCartItem = cartRepository.findByUserAndProduct(user, product);  // If the product is already in the cart, update the quantity

            if (existingCartItem != null) {
                // If the product is already in the cart, update the quantity
                existingCartItem.setQuantity(existingCartItem.getQuantity() + cartRequest.getQuantity());
                cartRepository.save(existingCartItem);
            } else {
                // If the product is not in the cart, create a new entry
                Cart cartItem = new Cart();
                cartItem.setUser(user);
                cartItem.setProduct(product);
                cartItem.setQuantity(cartRequest.getQuantity());
                //cartItem.setProductCost(product.getProductCost());
                cartRepository.save(cartItem);
            }
            return true;
        }

        return false;
    }

    public List<CartItemResponse> getCartItems(Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            List<Cart> cartItems = cartRepository.findByUser(user);
            return mapCartItemsToResponse(cartItems);
        }

        return Collections.emptyList();
    }

    public boolean updateCartItemQuantity(Long userId, CartRequest cartRequest) {
        User user = userRepository.findById(userId).orElse(null);
        Product product = productRepository.findById(cartRequest.getProductId()).orElse(null);

        if (user != null && product != null) {
            Cart cartItem = cartRepository.findByUserAndProduct(user, product);
            if (cartItem != null) {
                cartItem.setQuantity(cartRequest.getQuantity());
                cartRepository.save(cartItem);
                return true;
            }
        }

        return false;
    }

    private List<CartItemResponse> mapCartItemsToResponse(List<Cart> cartItems) {
        return cartItems.stream()
                .map(cartItem -> new CartItemResponse(
                        cartItem.getCartId(),

                        cartItem.getProduct().getProductName(),
                        //cartItem.getUser().getUsername(),    // Get user's name
                        cartItem.getQuantity(),
                        cartItem.getProduct().getProductCost()


                ))
                .collect(Collectors.toList());
    }


}
