package com.perfulandia.perfulandia.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "Venta")
public class Sale {
    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    private int cantidad;
    private String fecha;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;
}
