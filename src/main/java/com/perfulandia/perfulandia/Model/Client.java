package com.perfulandia.perfulandia.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Entity

@DiscriminatorValue("CLIENTE")
public class Client extends User{
    public boolean puedeGestionarEnvios() { return true; }

}
