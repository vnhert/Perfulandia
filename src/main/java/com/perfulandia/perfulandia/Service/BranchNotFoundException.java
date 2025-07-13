package com.perfulandia.perfulandia.Service; // Ensure this package matches your service

public class BranchNotFoundException extends RuntimeException {
    public BranchNotFoundException(String message) {
        super(message);
    }
}