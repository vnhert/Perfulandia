package com.perfulandia.perfulandia.Model;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("EMPLEADO_VENTAS")
@NoArgsConstructor
public class EmpleadoVentas extends User{
    public boolean puedeGestionarUsuarios() { return false; }
    public boolean puedeGestionarVentas() { return true; }


}