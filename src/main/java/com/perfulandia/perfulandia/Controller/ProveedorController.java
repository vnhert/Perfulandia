package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Model.Proveedor;
import com.perfulandia.perfulandia.Model.ProveedorActionRequest;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Proveedores", description = "Operaciones relacionadas con los proveedores")
@RestController
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @Operation(summary = "Obtener todos los proveedores", description = "Devuelve una lista de todos los proveedores.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista obtenida con éxito"),
            @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    @GetMapping
    public CollectionModel<EntityModel<Proveedor>> getProviders(@RequestBody User solicitante) {
        List<EntityModel<Proveedor>> providers = proveedorService.getProviders(solicitante).stream()
                .map(provider -> EntityModel.of(provider,
                        linkTo(methodOn(ProveedorController.class).getProvider(solicitante, provider.getId())).withSelfRel(),
                        linkTo(methodOn(ProveedorController.class).getProviders(solicitante)).withRel("proveedores")))
                .collect(Collectors.toList());

        return CollectionModel.of(providers,
                linkTo(methodOn(ProveedorController.class).getProviders(solicitante)).withSelfRel());
    }

    @Operation(summary = "Obtener un proveedor por ID", description = "Devuelve los detalles de un proveedor específico.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Proveedor obtenido con éxito"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<Proveedor> getProvider(@RequestBody User solicitante, @PathVariable int id) {
        Proveedor provider = proveedorService.getProvider(solicitante, id);
        return EntityModel.of(provider,
                linkTo(methodOn(ProveedorController.class).getProvider(solicitante, id)).withSelfRel(),
                linkTo(methodOn(ProveedorController.class).getProviders(solicitante)).withRel("proveedores"));
    }

    @Operation(summary = "Agregar un nuevo proveedor", description = "Registra un nuevo proveedor en el sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Proveedor creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public String addProvider(@RequestBody ProveedorActionRequest request) {
        return proveedorService.addProvider(request.getSolicitante(), request.getProveedor());
    }

    @Operation(summary = "Actualizar un proveedor", description = "Actualiza los detalles de un proveedor existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Proveedor actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    @PutMapping("/{id}")
    public String updateProvider(@RequestBody ProveedorActionRequest request, @PathVariable int id) {
        return proveedorService.updateProvider(request.getSolicitante(), id, request.getProveedor());
    }

    @Operation(summary = "Eliminar un proveedor", description = "Elimina un proveedor específico del sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Proveedor eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Proveedor no encontrado")
    })
    @DeleteMapping("/{id}")
    public String deleteProvider(@RequestBody User solicitante, @PathVariable int id) {
        return proveedorService.deleteProvider(solicitante, id);
    }
}
