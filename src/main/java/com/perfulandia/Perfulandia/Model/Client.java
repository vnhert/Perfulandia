package com.perfulandia.Perfulandia.Model;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Client {
    @Id
    private int id;
    private String nombre;
    private String email;
    private String direccion;
    private String telefono;
}
