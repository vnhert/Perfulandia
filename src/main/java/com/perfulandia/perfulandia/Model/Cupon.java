package com.perfulandia.perfulandia.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Cupon {
    @Id
    private String codigo;
    private double descuento;
    private boolean activo;
}
