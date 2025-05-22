package com.perfulandia.perfulandia.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity



@Table(name = "Envio")
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String estado;
    private Date fechaEntrega;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client cliente;

    @OneToMany(mappedBy = "ship", cascade = CascadeType.ALL)
    private java.util.List<ShipProduct> productos;
}
