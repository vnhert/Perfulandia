package com.perfulandia.perfulandia.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Product {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
}
