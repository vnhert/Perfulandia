package com.perfulandia.perfulandia.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class perfulandiaController {
    @GetMapping("/perfulandia")
    public String perfulandia() {
        return "perfulandia";
    }
}
