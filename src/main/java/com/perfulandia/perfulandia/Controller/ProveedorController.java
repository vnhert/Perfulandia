package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Model.ProveedorActionRequest;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Service.ProductService;
import com.perfulandia.perfulandia.Service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

@Tag(name = "Proveedores", description = "Operaciones relacionadas con los proveedores")
@RequestMapping("/proveedores")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    @Operation(summary = "Obtener todos los proveedores")
    @GetMapping
    public String getProviders(@RequestBody User solicitante) {
        return proveedorService.getProviders(solicitante);
    }
    @Operation(summary = "Obtener un proveedor por ID")
    @GetMapping("/{id}")
    public String getProvider(@RequestBody User solicitante, @PathVariable int id) {
        return proveedorService.getProvider(solicitante, id);
    }
    @Operation(summary = "Agregar un nuevo proveedor")
    @PostMapping
    public String addProvider(@RequestBody ProveedorActionRequest request) {
        return proveedorService.addProvider(request.getSolicitante(), request.getProveedor());
    }
    @Operation(summary = "Actualizar un proveedor")
    @PutMapping("/{id}")
    public String updateProvider(@RequestBody ProveedorActionRequest request, @PathVariable int id) {
        return proveedorService.updateProvider(request.getSolicitante(), id, request.getProveedor());
    }
    @Operation(summary = "Eliminar un proveedor")
    @DeleteMapping("/{id}")
    public String deleteProvider(@RequestBody User solicitante, @PathVariable int id) {
        return proveedorService.deleteProvider(solicitante, id);
    }
}
