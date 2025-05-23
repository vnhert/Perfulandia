package com.perfulandia.perfulandia.Model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comentario;
    private int calificacion; // 1 a 5

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Product producto;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Client cliente;
}
