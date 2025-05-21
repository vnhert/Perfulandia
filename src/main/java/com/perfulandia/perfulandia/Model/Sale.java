package com.perfulandia.perfulandia.Model;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Venta")
public class Sale {
    private int id;
    private Product product;
    private Client client;
    private int cantidad;
    private String fecha;
}
