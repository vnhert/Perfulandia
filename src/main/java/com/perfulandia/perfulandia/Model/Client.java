package com.perfulandia.perfulandia.Model;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Entity

@DiscriminatorValue("CLIENTE")
public class Client extends User{
    public boolean puedeVerProductos() { return true; }

}
