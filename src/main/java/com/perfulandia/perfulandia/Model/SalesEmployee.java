package com.perfulandia.perfulandia.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

<<<<<<< HEAD:src/main/java/com/perfulandia/perfulandia/Model/GerenteSucursal.java
@Entity
@DiscriminatorValue("GERENTE_SUCURSAL")
@NoArgsConstructor
public class GerenteSucursal extends User{
    public boolean puedeGestionarUsuarios() { return false; }
    public boolean puedeGestionarProductos() { return true; }
    public boolean puedeGestionarSucursales() { return true; }

=======
public class SalesEmployee extends User{
>>>>>>> 6cbf6e59fbe712e29b6debeddb50ead12b0f203c:src/main/java/com/perfulandia/perfulandia/Model/SalesEmployee.java
}
