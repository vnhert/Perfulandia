package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Assembler.OrderAssembler;
import com.perfulandia.perfulandia.Model.Order;
import com.perfulandia.perfulandia.Model.OrderActionRequest;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Órdenes", description = "Operaciones relacionadas con las órdenes")
@RestController
@RequestMapping("/pedidos")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderAssembler assembler;

    @Operation(summary = "Obtener todas las órdenes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Órdenes obtenidas con éxito"),
            @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    @GetMapping
    public CollectionModel<EntityModel<Order>> getOrders(@RequestBody User solicitante) {
        List<EntityModel<Order>> orders = orderService.getOrders(solicitante).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(orders,
                linkTo(methodOn(OrderController.class).getOrders(solicitante)).withSelfRel());
    }

    @Operation(summary = "Obtener una orden por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Orden obtenida con éxito"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada")
    })
    @GetMapping("/{id}")
    public EntityModel<Order> getOrder(@RequestBody User solicitante, @PathVariable int id) {
        Order order = orderService.getOrder(solicitante, id);
        return assembler.toModel(order);
    }

    @Operation(summary = "Agregar una nueva orden")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Orden creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public EntityModel<Order> addOrder(@RequestBody OrderActionRequest request) {
        Order newOrder = orderService.addOrder(request.getSolicitante(), request.getOrder());
        return assembler.toModel(newOrder);
    }

    @Operation(summary = "Eliminar una orden")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Orden eliminada con éxito"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada")
    })
    @DeleteMapping("/{id}")
    public String deleteOrder(@RequestBody User solicitante, @PathVariable int id) {
        return orderService.deleteOrder(solicitante, id);
    }

    @Operation(summary = "Actualizar una orden")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Orden actualizada con éxito"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada")
    })
    @PutMapping("/{id}")
    public EntityModel<Order> updateOrder(@RequestBody OrderActionRequest request, @PathVariable int id) {
        Order updatedOrder = orderService.updateOrder(request.getSolicitante(), id, request.getOrder());
        return assembler.toModel(updatedOrder);
    }

    @Operation(summary = "Actualizar estado de una orden")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estado de la orden actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada")
    })
    @PutMapping("/{id}/estado")
    public EntityModel<Order> updateStateOrder(@RequestBody OrderActionRequest request, @PathVariable int id) {
        Order updatedOrder = orderService.updateStateOrder(request.getSolicitante(), id, request.getOrder());
        return assembler.toModel(updatedOrder);
    }

    @Operation(summary = "Obtener órdenes por estado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Órdenes obtenidas con éxito"),
            @ApiResponse(responseCode = "404", description = "No se encontraron órdenes con el estado especificado")
    })
    @GetMapping("/estado/{estado}")
    public CollectionModel<EntityModel<Order>> getOrdersByEstado(@PathVariable String estado) {
        List<EntityModel<Order>> orders = orderService.findByEstado(estado).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(orders,
                linkTo(methodOn(OrderController.class).getOrdersByEstado(estado)).withSelfRel());
    }
}
