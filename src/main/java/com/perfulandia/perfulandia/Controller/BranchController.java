package com.perfulandia.perfulandia.Controller;
import com.perfulandia.perfulandia.Model.Branch;

import com.perfulandia.perfulandia.Model.BranchActionRequest;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Sucursales", description = "Operaciones relacionadas con las sucursales")

@RestController
@RequestMapping("/sucursales")
public class BranchController {
    @Autowired
    private BranchService branchService;

    @Operation(summary = "Obtener todas las sucursales")
    @GetMapping
    public String getBranchs(@RequestBody User solicitante) {
        return branchService.getBranchs(solicitante);
    }

    @Operation(summary = "Obtener una sucursal por ID")
    @GetMapping("/{id}")
    public String getBranch(@RequestBody User solicitante, @PathVariable int id) {
        return branchService.getBranch(solicitante, id);
    }
    @Operation(summary = "Agregar una nueva sucursal")
    @PostMapping
    public String addBranch(@RequestBody BranchActionRequest request) {
        return branchService.addBranch(request.getSolicitante(), request.getBranch());
    }
    @Operation(summary = "Actualizar una sucursal")
    @PutMapping("/{id}")
    public String updateBranch(@RequestBody BranchActionRequest request, @PathVariable int id) {
        return branchService.updateBranch(request.getSolicitante(), id, request.getBranch());
    }
    @Operation(summary = "Eliminar una sucursal")
    @DeleteMapping("/{id}")
    public String deleteBranch(@RequestBody User solicitante, @PathVariable int id) {
        return branchService.deleteBranch(solicitante, id);
    }
}
