package com.perfulandia.perfulandia.Service; // Or your designated exceptions package

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String message) {
        super(message);
    }
}
