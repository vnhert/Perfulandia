package com.perfulandia.perfulandia.Model;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Model.Client;
import com.perfulandia.perfulandia.Model.Product;
import lombok.Data;

import java.util.List;
@Data
public class CrearEnvioRequest {
    public User solicitante;
    public Client cliente;
    public List<Product> productos;
    public List<Integer> cantidades;
    public String cupon;

}
