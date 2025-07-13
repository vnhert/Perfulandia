package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Model.Client;
import com.perfulandia.perfulandia.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.Parameter;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Clientes", description = "Operaciones relacionadas con los clientes")
@RestController
@RequestMapping("/clientes")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Operation(summary = "Obtener todos los clientes", description = "Devuelve una lista de todos los clientes.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista obtenida con éxito"),
            @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    @GetMapping
    public CollectionModel<EntityModel<Client>> getClients() {
        List<EntityModel<Client>> clients = clientService.getClients().stream()
                .map(client -> EntityModel.of(client,
                        linkTo(methodOn(ClientController.class).getClient(client.getId())).withSelfRel(),
                        linkTo(methodOn(ClientController.class).getClients()).withRel("clientes")))
                .collect(Collectors.toList());

        return CollectionModel.of(clients,
                linkTo(methodOn(ClientController.class).getClients()).withSelfRel());
    }

    @Operation(summary = "Obtener un cliente por ID", description = "Devuelve los detalles de un cliente específico.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente obtenido con éxito"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<Client> getClient(
            @Parameter(description = "ID del cliente", required = true)
            @PathVariable int id) {
        Client client = clientService.getClient(id);
        return EntityModel.of(client,
                linkTo(methodOn(ClientController.class).getClient(id)).withSelfRel(),
                linkTo(methodOn(ClientController.class).getClients()).withRel("clientes"));
    }

    @Operation(summary = "Agregar un nuevo cliente", description = "Registra un nuevo cliente en el sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cliente creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public String addClient(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del cliente a agregar", required = true)
            @RequestBody Client client) {
        return clientService.addClient(client);
    }

    @Operation(summary = "Actualizar un cliente", description = "Actualiza los detalles de un cliente existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @PutMapping("/{id}")
    public String updateClient(
            @Parameter(description = "ID del cliente", required = true)
            @PathVariable int id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del cliente a actualizar", required = true)
            @RequestBody Client client) {
        return clientService.updateClient(id, client);
    }

    @Operation(summary = "Eliminar un cliente", description = "Elimina un cliente específico del sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @DeleteMapping("/{id}")
    public String deleteClient(
            @Parameter(description = "ID del cliente", required = true)
            @PathVariable int id) {
        return clientService.deleteClient(id);
    }
}