package com.perfulandia.perfulandia.Model;

<<<<<<< HEAD:src/main/java/com/perfulandia/perfulandia/Model/EmpleadoVentas.java
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("EMPLEADO_VENTAS")
@NoArgsConstructor
public class EmpleadoVentas extends User{
    public boolean puedeGestionarUsuarios() { return false; }
    public boolean puedeGestionarVentas() { return true; }
=======
public class BranchManager extends User{
>>>>>>> 6cbf6e59fbe712e29b6debeddb50ead12b0f203c:src/main/java/com/perfulandia/perfulandia/Model/BranchManager.java
}
