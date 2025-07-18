package com.perfulandia.perfulandia.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Pedido")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String estado;
    private Date fechaEntrega;
    private String cantidad;
    private boolean autorizado;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
