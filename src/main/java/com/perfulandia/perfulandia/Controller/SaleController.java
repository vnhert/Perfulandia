package com.perfulandia.perfulandia.Controller;
import com.perfulandia.perfulandia.Model.SaleActionRequest;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
@Tag(name = "Ventas", description = "Operaciones relacionadas con las ventas")
@RestController
@RequestMapping("/ventas")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @Operation(summary = "Obtener todas las ventas")
    @GetMapping
    public String getSales(@RequestBody User solicitante) {
        return saleService.getSales(solicitante);
    }

    @Operation(summary = "Obtener una venta por ID")
    @GetMapping("/{id}")
    public String getSale(@RequestBody User solicitante, @PathVariable int id) {
        return saleService.getSale(solicitante, id);
    }

    @Operation(summary = "Registrar una nueva venta")
    @PostMapping
    public String addSale(@RequestBody SaleActionRequest request) {
        return saleService.addSale(request.getSolicitante(), request.getSale());
    }
    @Operation(summary = "Eliminar una venta")
    @DeleteMapping("/{id}")
    public String deleteSale(@RequestBody User solicitante, @PathVariable int id) {
        return saleService.deleteSale(solicitante, id);
    }
    @Operation(summary = "Actualizar una venta")
    @PutMapping("/{id}")
    public String updateSale(@RequestBody SaleActionRequest request, @PathVariable int id) {
        return saleService.updateSale(request.getSolicitante(), id, request.getSale());
    }
}
