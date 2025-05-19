package com.perfulandia.perfulandia.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private int id;
    private String nombre;
    private String email;
    private String direccion;
    private String telefono;
}
