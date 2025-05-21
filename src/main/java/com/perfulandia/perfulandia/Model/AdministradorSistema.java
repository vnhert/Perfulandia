package com.perfulandia.perfulandia.Model;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("ADMINISTRADOR")
public class AdministradorSistema extends User{
    public boolean puedeGestionarUsuarios() { return true; }
    public boolean puedeGestionarSucursales() { return false; }
}
