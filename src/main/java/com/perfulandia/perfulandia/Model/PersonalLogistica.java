package com.perfulandia.perfulandia.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;
import lombok.NoArgsConstructor;


@Entity
@DiscriminatorValue("PERSONAL_LOGISTICA")
@NoArgsConstructor
public class PersonalLogistica extends User{
    public boolean puedeGestionarUsuarios() { return false; }
    public boolean puedeGestionarVentas() { return false; }
    public boolean puedeGestionarProveedores() { return true; }
    public boolean puedeGestionarEnvios() { return true; }
    public boolean puedeGestionarPedidos() { return true; }
    public boolean puedeActualizarPedido() { return true; }
}
