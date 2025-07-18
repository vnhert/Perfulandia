package com.perfulandia.perfulandia.Model;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "rol"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AdministradorSistema.class, name = "ADMINISTRADOR"),
        @JsonSubTypes.Type(value = EmpleadoVentas.class, name = "EMPLEADO_VENTAS"),
        @JsonSubTypes.Type(value = GerenteSucursal.class, name = "GERENTE_SUCURSAL"),
        @JsonSubTypes.Type(value = PersonalLogistica.class, name = "PERSONAL_LOGISTICA"),
        @JsonSubTypes.Type(value = Client.class, name = "CLIENTE")
})

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuario")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "rol")
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQ")
    @SequenceGenerator(name = "usuario_seq", sequenceName = "USUARIO_SEQ", allocationSize = 1)
    private int id;
    private String nombre;
    private String correo;
    private String contraseña;

    // --- ¡¡AÑADIDO!! ---
    @ManyToOne // Un usuario pertenece a UNA sucursal
    @JoinColumn(name = "branch_id") // Columna de la clave foránea en la tabla 'usuario'
    private Branch branch; // <-- ¡Añade esta línea!

    private String telefono; // <-- Añade esta línea para setTelefono()
    private String direccion; // <-- Añade esta línea para setDireccion()


    public boolean puedeGestionarUsuarios() { return false; }
    public boolean puedeGestionarProductos() { return false; }
    public boolean puedeVerProductos() { return false; }
    public boolean puedeGestionarSucursales() { return false; }
    public boolean puedeGestionarVentas() { return false; }
    public boolean puedeGestionarProveedores() { return false; }
    public boolean puedeGestionarPedidos() { return false; }

    public boolean puedeGestionarEnvios() { return false; }
    public boolean puedeActualizarPedido() { return false; }
    public boolean puedeCrearEnvio() { return false; }
    public boolean puedeDejarReseñas() { return false; }
    public boolean puedeVerReseñas() { return false; }

}
