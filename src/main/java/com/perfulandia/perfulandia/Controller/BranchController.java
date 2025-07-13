package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Model.Branch;
import com.perfulandia.perfulandia.Model.BranchActionRequest;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Sucursales", description = "Operaciones relacionadas con las sucursales")
@RestController
@RequestMapping("/sucursales")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @Operation(summary = "Obtener todas las sucursales", description = "Devuelve una lista de todas las sucursales.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista obtenida con éxito"),
            @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    @GetMapping
    public CollectionModel<EntityModel<Branch>> getBranches(
            @RequestBody(description = "Usuario solicitante", required = true) User solicitante) {
        List<EntityModel<Branch>> branches = branchService.getBranches(solicitante).stream()
                .map(branch -> EntityModel.of(branch,
                        linkTo(methodOn(BranchController.class).getBranch(solicitante, branch.getId())).withSelfRel(),
                        linkTo(methodOn(BranchController.class).getBranches(solicitante)).withRel("sucursales")))
                .collect(Collectors.toList());

        return CollectionModel.of(branches,
                linkTo(methodOn(BranchController.class).getBranches(solicitante)).withSelfRel());
    }

    @Operation(summary = "Obtener una sucursal por ID", description = "Devuelve los detalles de una sucursal específica.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucursal obtenida con éxito"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @GetMapping("/{id}")
    public EntityModel<Branch> getBranch(
            @RequestBody(description = "Usuario solicitante", required = true) User solicitante,
            @Parameter(description = "ID de la sucursal", required = true) @PathVariable int id) {
        Branch branch = branchService.getBranch(solicitante, id);
        return EntityModel.of(branch,
                linkTo(methodOn(BranchController.class).getBranch(solicitante, id)).withSelfRel(),
                linkTo(methodOn(BranchController.class).getBranches(solicitante)).withRel("sucursales"));
    }

    @Operation(summary = "Agregar una nueva sucursal", description = "Registra una nueva sucursal en el sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Sucursal creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public EntityModel<Branch> addBranch(
            @RequestBody(description = "Solicitud para agregar una sucursal", required = true) BranchActionRequest request) {
        Branch newBranch = branchService.addBranch(request.getSolicitante(), request.getBranch());
        return EntityModel.of(newBranch,
                linkTo(methodOn(BranchController.class).getBranch(request.getSolicitante(), newBranch.getId())).withSelfRel(),
                linkTo(methodOn(BranchController.class).getBranches(request.getSolicitante())).withRel("sucursales"));
    }

    @Operation(summary = "Actualizar una sucursal", description = "Actualiza los detalles de una sucursal existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucursal actualizada con éxito"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @PutMapping("/{id}")
    public EntityModel<Branch> updateBranch(
            @RequestBody(description = "Solicitud para actualizar una sucursal", required = true) BranchActionRequest request,
            @Parameter(description = "ID de la sucursal", required = true) @PathVariable int id) {
        Branch updatedBranch = branchService.updateBranch(request.getSolicitante(), id, request.getBranch());
        return EntityModel.of(updatedBranch,
                linkTo(methodOn(BranchController.class).getBranch(request.getSolicitante(), id)).withSelfRel(),
                linkTo(methodOn(BranchController.class).getBranches(request.getSolicitante())).withRel("sucursales"));
    }

    @Operation(summary = "Eliminar una sucursal", description = "Elimina una sucursal específica del sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Sucursal eliminada con éxito"),
            @ApiResponse(responseCode = "404", description = "Sucursal no encontrada")
    })
    @DeleteMapping("/{id}")
    public String deleteBranch(
            @RequestBody(description = "Usuario solicitante", required = true) User solicitante,
            @Parameter(description = "ID de la sucursal", required = true) @PathVariable int id) {
        branchService.deleteBranch(solicitante, id);
        return "Sucursal eliminada con éxito";
    }
}