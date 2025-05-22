package com.perfulandia.perfulandia.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;


@Entity
@DiscriminatorValue("GERENTE_SUCURSAL")
@NoArgsConstructor
public class GerenteSucursal extends User{
    public boolean puedeGestionarUsuarios() { return false; }
    public boolean puedeGestionarProductos() { return true; }
    public boolean puedeGestionarSucursales() { return true; }


}