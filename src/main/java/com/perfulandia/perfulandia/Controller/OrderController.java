package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Assembler.OrderAssembler;
import com.perfulandia.perfulandia.Model.Order;
import com.perfulandia.perfulandia.Model.OrderActionRequest;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;

import java.util.List;
import java.util.stream.Collectors;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
@Tag(name = "Órdenes", description = "Operaciones relacionadas con las órdenes")

@RestController
@RequestMapping("/pedidos")

public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderAssembler assembler;

    @Operation(summary = "Obtener todas las órdenes")
    CollectionModel<EntityModel<Order>> getOrders(@RequestBody User solicitante) {
        List<EntityModel<Order>> orders = orderService.getOrders(solicitante).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(orders);
    }

    @Operation(summary = "Obtener una orden por ID")
    @GetMapping("/{id}")
    public EntityModel<Order> getOrder(@RequestBody User solicitante, @PathVariable int id) {
        Order order = orderService.getOrder(solicitante, id);
        return assembler.toModel(order);
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

    @Operation(summary = "Obtener órdenes por estado")
    @GetMapping("/estado/{estado}")
    public CollectionModel<EntityModel<Order>> getOrdersByEstado(@PathVariable String estado) {
        List<EntityModel<Order>> orders = orderService.findByEstado(estado).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(orders,
                linkTo(methodOn(OrderController.class).getOrdersByEstado(estado)).withSelfRel());
    }

}
