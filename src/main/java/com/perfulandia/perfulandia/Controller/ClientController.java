package com.perfulandia.perfulandia.Controller;


import com.perfulandia.perfulandia.Model.Client;
import com.perfulandia.perfulandia.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Tag(name = "Clientes", description = "Operaciones relacionadas con los clientes")
@RestController
@RequestMapping("/clientes")
public class ClientController {
    @Autowired
    ClientService clientService;

    @Operation(summary = "Obtener todos los clientes")
    @GetMapping()
    public String getClients(){
        return clientService.getClients();
    }

    @Operation(summary = "Obtener un cliente por ID")
    @GetMapping("/{id}")
    public String getClient(@PathVariable int id){
        return clientService.getClient(id);
    }

    @Operation(summary = "Agregar un nuevo cliente")
    @PostMapping()
    public String addClient(@RequestBody Client client)
    {
        return clientService.addClient(client);
    }


    @Operation(summary = "Eliminar un cliente")
    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable int id){
        return clientService.deleteClient(id);
    }

    @Operation(summary = "Actualizar un cliente")
    @PutMapping("/{id}")
    public String updateClient(@PathVariable int id, @RequestBody Client client) {
        return clientService.updateClient(id, client);
    }
}
