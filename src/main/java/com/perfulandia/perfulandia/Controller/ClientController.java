package com.perfulandia.perfulandia.Controller; // Or your actual controller package
import com.perfulandia.perfulandia.Model.Client; // Assuming your Client model is here
import com.perfulandia.perfulandia.Service.ClientService; // Assuming your ClientService is here
import com.perfulandia.perfulandia.Service.ClientNotFoundException; // For @ResponseStatus
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter; // Add this import for @Parameter
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus; // For HttpStatus
import org.springframework.http.ResponseEntity; // For ResponseEntity
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
@RequestMapping("/clients") // Good practice to have a base path for the resource
public class ClientController {

    private final ClientService clientService; // Use final and constructor injection

    public ClientController(ClientService clientService) { // Constructor for dependency injection
        this.clientService = clientService;
    }

    @Operation(summary = "Obtener todos los clientes", description = "Devuelve una lista de todos los clientes.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista obtenida con éxito"),
            // Assuming you'll add authorization to ClientService similar to BranchService later
            @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    @GetMapping // No need for "/clients" if @RequestMapping is used at class level
    public CollectionModel<EntityModel<Client>> getClients() {
        List<EntityModel<Client>> clients = clientService.getClients().stream()
                .map(client -> EntityModel.of(client,
                        linkTo(methodOn(ClientController.class).getClient(client.getId())).withSelfRel(),
                        linkTo(methodOn(ClientController.class).getClients()).withRel("clients")))
                .collect(Collectors.toList());

        return CollectionModel.of(clients, linkTo(methodOn(ClientController.class).getClients()).withSelfRel());
    }

    @Operation(summary = "Obtener un cliente por ID", description = "Devuelve los detalles de un cliente específico.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente obtenido con éxito"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<Client> getClient(
            @Parameter(description = "ID del cliente", required = true) @PathVariable int id) {
        Client client = clientService.getClient(id); // ClientService.getClient now returns Client
        return EntityModel.of(client,
                linkTo(methodOn(ClientController.class).getClient(client.getId())).withSelfRel(),
                linkTo(methodOn(ClientController.class).getClients()).withRel("clients"));
    }

    @Operation(summary = "Agregar un nuevo cliente", description = "Registra un nuevo cliente en el sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Cliente creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public ResponseEntity<EntityModel<Client>> addClient(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del cliente a agregar", required = true)
            @RequestBody Client client) {

        Client newClient = clientService.addClient(client); // ClientService.addClient now returns Client

        // Build HATEOAS links for the newly created client
        EntityModel<Client> clientEntityModel = EntityModel.of(newClient,
                linkTo(methodOn(ClientController.class).getClient(newClient.getId())).withSelfRel(),
                linkTo(methodOn(ClientController.class).getClients()).withRel("clients"));

        // Return 201 Created status with the new client and its links
        return ResponseEntity
                .created(linkTo(methodOn(ClientController.class).getClient(newClient.getId())).toUri())
                .body(clientEntityModel);
    }

    @Operation(summary = "Actualizar un cliente", description = "Actualiza los detalles de un cliente existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @PutMapping("/{id}")
    public EntityModel<Client> updateClient(
            @Parameter(description = "ID del cliente", required = true) @PathVariable int id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Datos del cliente a actualizar", required = true)
            @RequestBody Client updatedClient) {

        Client client = clientService.updateClient(id, updatedClient); // ClientService.updateClient returns Client

        return EntityModel.of(client,
                linkTo(methodOn(ClientController.class).getClient(client.getId())).withSelfRel(),
                linkTo(methodOn(ClientController.class).getClients()).withRel("clients"));
    }

    @Operation(summary = "Eliminar un cliente", description = "Elimina un cliente específico del sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Cliente eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(
            @Parameter(description = "ID del cliente", required = true) @PathVariable int id) {

        clientService.deleteClient(id); // ClientService.deleteClient now returns void (or throws exception)
        return ResponseEntity.noContent().build(); // Return 204 No Content for successful deletion
    }

    // --- Exception Handling for ClientNotFoundException ---
    // This handler catches ClientNotFoundException and returns a 404 Not Found
    @ExceptionHandler(ClientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleClientNotFoundException(ClientNotFoundException ex) {
        return ex.getMessage();
    }
}