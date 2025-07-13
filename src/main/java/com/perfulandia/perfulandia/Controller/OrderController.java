package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Assembler.OrderAssembler;
import com.perfulandia.perfulandia.Model.Order;
import com.perfulandia.perfulandia.Model.OrderActionRequest;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Service.OrderService;
import com.perfulandia.perfulandia.Service.OrderNotFoundException;
import com.perfulandia.perfulandia.Service.UnauthorizedException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter; // **ADDED THIS IMPORT**
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name = "Órdenes", description = "Operaciones relacionadas con las órdenes")
@RestController
@RequestMapping("/pedidos")
public class OrderController {

    private final OrderService orderService;
    private final OrderAssembler assembler;

    public OrderController(OrderService orderService, OrderAssembler assembler) {
        this.orderService = orderService;
        this.assembler = assembler;
    }

    @Operation(summary = "Obtener todas las órdenes", description = "Devuelve una lista de todas las órdenes para el usuario autorizado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Órdenes obtenidas con éxito"),
            @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    @GetMapping
    public CollectionModel<EntityModel<Order>> getOrders() { // No User parameter here
        List<EntityModel<Order>> orders = orderService.getOrders().stream() // Corrected: no User passed
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(orders,
                linkTo(methodOn(OrderController.class).getOrders()).withSelfRel());
    }

    @Operation(summary = "Obtener una orden por ID", description = "Devuelve los detalles de una orden específica.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Orden obtenida con éxito"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada"),
            @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    @GetMapping("/{id}")
    public EntityModel<Order> getOrder(
            @Parameter(description = "ID del pedido", required = true) @PathVariable int id) { // **ADDED @Parameter**
        Order order = orderService.getOrder(id); // Corrected: no User passed
        return assembler.toModel(order);
    }

    @Operation(summary = "Agregar una nueva orden", description = "Crea una nueva orden en el sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Orden creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida"),
            @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    @PostMapping
    public ResponseEntity<EntityModel<Order>> addOrder(@RequestBody OrderActionRequest request) {
        Order newOrder = orderService.addOrder(request.getSolicitante(), request.getOrder());
        EntityModel<Order> orderEntityModel = assembler.toModel(newOrder);

        return ResponseEntity
                .created(linkTo(methodOn(OrderController.class).getOrder(newOrder.getId())).toUri())
                .body(orderEntityModel);
    }

    @Operation(summary = "Eliminar una orden", description = "Elimina una orden específica del sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Orden eliminada con éxito"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada"),
            @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(
            @Parameter(description = "ID del pedido", required = true) @PathVariable int id, // **ADDED @Parameter**
            @RequestBody User solicitante) {
        orderService.deleteOrder(solicitante, id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Actualizar una orden", description = "Actualiza los detalles de una orden existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Orden actualizada con éxito"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada"),
            @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    @PutMapping("/{id}")
    public EntityModel<Order> updateOrder(
            @Parameter(description = "ID del pedido", required = true) @PathVariable int id, // **ADDED @Parameter**
            @RequestBody OrderActionRequest request) {
        Order updatedOrder = orderService.updateOrder(request.getSolicitante(), id, request.getOrder());
        return assembler.toModel(updatedOrder);
    }

    @Operation(summary = "Actualizar estado de una orden", description = "Actualiza el estado de una orden específica.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estado de la orden actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada"),
            @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    @PutMapping("/{id}/estado")
    public EntityModel<Order> updateStateOrder(
            @Parameter(description = "ID del pedido", required = true) @PathVariable int id, // **ADDED @Parameter**
            @RequestBody OrderActionRequest request) {
        Order updatedOrder = orderService.updateStateOrder(request.getSolicitante(), id, request.getOrder());
        return assembler.toModel(updatedOrder);
    }

    @Operation(summary = "Obtener órdenes por estado", description = "Devuelve una lista de órdenes filtradas por estado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Órdenes obtenidas con éxito"),
            @ApiResponse(responseCode = "404", description = "No se encontraron órdenes con el estado especificado")
    })
    @GetMapping("/estado/{estado}")
    public CollectionModel<EntityModel<Order>> getOrdersByEstado(
            @Parameter(description = "Estado del pedido (ej. 'Pendiente', 'En proceso')", required = true) @PathVariable String estado) { // **ADDED @Parameter**
        List<EntityModel<Order>> orders = orderService.findByEstado(estado).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(orders,
                linkTo(methodOn(OrderController.class).getOrdersByEstado(estado)).withSelfRel());
    }

    // --- Exception Handling ---
    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleOrderNotFoundException(OrderNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN) // 403 Forbidden
    public String handleUnauthorizedException(UnauthorizedException ex) {
        return ex.getMessage();
    }
}