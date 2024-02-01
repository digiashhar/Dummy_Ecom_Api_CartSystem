package com.register.registration.repository;

import com.register.registration.entity.Cart;
import com.register.registration.entity.Product;
import com.register.registration.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    // Add custom query methods if needed
    Cart findByUserAndProduct(User user, Product product);

    List<Cart> findByUser(User user);
}
