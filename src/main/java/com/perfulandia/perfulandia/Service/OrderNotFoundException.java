package com.perfulandia.perfulandia.Service; // Adjust package if different

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {
        super(message);
    }
}