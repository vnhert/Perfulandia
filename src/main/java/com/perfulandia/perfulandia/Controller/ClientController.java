package com.perfulandia.perfulandia.Controller;


import com.perfulandia.perfulandia.Assembler.ClientAssembler;
import com.perfulandia.perfulandia.Model.Client;
import com.perfulandia.perfulandia.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;

import java.util.List;
import java.util.stream.Collectors
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@Tag(name = "Clientes", description = "Operaciones relacionadas con los clientes")
@RestController
@RequestMapping("/clientes")
public class ClientController {
    @Autowired
    ClientService clientService;
    @Autowired
    private ClientAssembler assembler;

    @Operation(summary = "Obtener todos los clientes")
    @GetMapping()
    public CollectionModel<EntityModel<Client>> getClients() {
        List<EntityModel<Client>> clients = clientService.getClients().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(clients);
    }

    @Operation(summary = "Obtener un cliente por ID")
    @GetMapping("/{id}")
    public EntityModel<Client> getClient(@PathVariable int id) {
        Client client = clientService.getClient(id);
        return assembler.toModel(client);
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
