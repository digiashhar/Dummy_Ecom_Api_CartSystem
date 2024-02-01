package com.register.registration.Controller;

import com.register.registration.Service.ServiceImpl.ProductService;
import com.register.registration.dto.ProductDTO;
import com.register.registration.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            String message = "Product with ID " + id + " not found";
            return ResponseEntity.status(404).body(message);
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody ProductDTO newProductDTO) {
        Product savedProduct = productService.createProduct(newProductDTO);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductDTO updatedProductDTO) {
        Product product = productService.updateProduct(id, updatedProductDTO);

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            String message = "Product with ID " + id + " not found";
            return ResponseEntity.status(404).body(message);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    //ADD TO CART:
    @PostMapping("/add-to-cart")
    public ResponseEntity<String> addToCart(@RequestParam long userId, @RequestParam long productId, @RequestParam int quantity) {
        return productService.addToCart(userId, productId, quantity);
    }

}
