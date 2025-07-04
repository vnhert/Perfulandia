package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Model.Order;
import com.perfulandia.perfulandia.Model.OrderActionRequest;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
@Tag(name = "Órdenes", description = "Operaciones relacionadas con las órdenes")

@RestController
@RequestMapping("/pedidos")

public class OrderController {
    @Autowired
    private OrderService orderService;

    @Operation(summary = "Obtener todas las órdenes")
    @GetMapping
    public String getOrders(@RequestBody User solicitante) {
        return orderService.getOrders(solicitante);
    }

    @Operation(summary = "Obtener una orden por ID")
    @GetMapping("/{id}")
    public String getOrder(@RequestBody User solicitante, @PathVariable int id) {
        return orderService.getOrder(solicitante, id);
    }
    @Operation(summary = "Agregar una nueva orden")
    @PostMapping
    public String addOrder(@RequestBody OrderActionRequest request) {
        return orderService.addOrder(request.getSolicitante(), request.getOrder());
    }

    @Operation(summary = "Eliminar una orden")
    @DeleteMapping("/{id}")
    public String deleteOrder(@RequestBody User solicitante, @PathVariable int id) {
        return orderService.deleteOrder(solicitante, id);
    }
    @Operation(summary = "Actualizar una orden")
    @PutMapping("/{id}")
    public String updateOrder(@RequestBody OrderActionRequest request, @PathVariable int id) {
        return orderService.updateOrder(request.getSolicitante(), id, request.getOrder());
    }
    @Operation(summary = "Actualizar estado de una orden")
    @PutMapping("/{id}/estado")
    public String updateStateOrder(@RequestBody OrderActionRequest request, @PathVariable int id) {
        return orderService.updateStateOrder(request.getSolicitante(), id, request.getOrder());
    }
}
