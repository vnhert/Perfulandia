package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Model.ProveedorActionRequest;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Service.ProductService;
import com.perfulandia.perfulandia.Service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/proveedores")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public String getProviders(@RequestBody User solicitante) {
        return proveedorService.getProviders(solicitante);
    }

    @GetMapping("/{id}")
    public String getProduct(@RequestBody User solicitante, @PathVariable int id) {
        return proveedorService.getProvider(solicitante, id);
    }

    @PostMapping
    public String addProduct(@RequestBody ProveedorActionRequest request) {
        return proveedorService.addProvider(request.getSolicitante(), request.getProveedor());
    }

    @PutMapping("/{id}")
    public String updateProduct(@RequestBody ProveedorActionRequest request, @PathVariable int id) {
        return proveedorService.updateProvider(request.getSolicitante(), id, request.getProveedor());
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@RequestBody User solicitante, @PathVariable int id) {
        return proveedorService.deleteProvider(solicitante, id);
    }
}
