package com.perfulandia.perfulandia.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Logistica {

    @Id
    private String id;

    private String estadoPedido;
    private String rutaOptimizada;
}
