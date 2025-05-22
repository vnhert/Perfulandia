package com.perfulandia.perfulandia.Model;


import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
@Table(name = "Ship_Product")
public class ShipProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ship_id")
    private Ship ship;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int cantidad;
}
