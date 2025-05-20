package com.perfulandia.Perfulandia.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {
    private int id;
    private String nombre;
    private String rol;//puede ser gerente sucursal, empleado etc.}
    private String correo;
    private String contraseña;
}
