package com.register.registration.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;

    private String productName;
    private double productCost;

    // Getter and Setter methods for productId
    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    // Getter and Setter methods for productName
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    // Getter and Setter methods for productCost
    public double getProductCost() {
        return this.productCost;
    }
    public void setProductCost(double productCost) {
        this.productCost = productCost;
    }
}
