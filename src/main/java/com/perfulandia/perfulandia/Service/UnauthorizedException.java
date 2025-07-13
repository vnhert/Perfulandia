package com.perfulandia.perfulandia.Service; // Ensure this package matches your service

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}