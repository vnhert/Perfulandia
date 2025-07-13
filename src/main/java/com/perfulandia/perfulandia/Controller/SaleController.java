package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Assembler.SaleAssembler;
import com.perfulandia.perfulandia.Model.Sale;
import com.perfulandia.perfulandia.Model.SaleActionRequest;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Service.SaleService;
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

@Tag(name = "Ventas", description = "Operaciones relacionadas con las ventas")
@RestController
@RequestMapping("/ventas")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @Autowired
    private SaleAssembler assembler;

    @Operation(summary = "Obtener todas las ventas", description = "Devuelve una lista de todas las ventas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista obtenida con éxito"),
            @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    @GetMapping
    public CollectionModel<EntityModel<Sale>> getSales() {
        List<EntityModel<Sale>> sales = saleService.getSales().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(sales,
                linkTo(methodOn(SaleController.class).getSales()).withSelfRel());
    }

    @Operation(summary = "Obtener una venta por ID", description = "Devuelve los detalles de una venta específica.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Venta obtenida con éxito"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    @GetMapping("/{id}")
    public EntityModel<Sale> getSale(@PathVariable int id) {
        Sale sale = saleService.getSale(id);
        return assembler.toModel(sale);
    }

    @Operation(summary = "Registrar una nueva venta", description = "Registra una nueva venta en el sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Venta creada con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public EntityModel<Sale> addSale(@RequestBody SaleActionRequest request) {
        Sale newSale = saleService.addSale(request.getSolicitante(), request.getSale());
        return assembler.toModel(newSale);
    }

    @Operation(summary = "Eliminar una venta", description = "Elimina una venta específica del sistema.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Venta eliminada con éxito"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    @DeleteMapping("/{id}")
    public String deleteSale(@RequestBody User solicitante, @PathVariable int id) {
        return saleService.deleteSale(solicitante, id);
    }

    @Operation(summary = "Actualizar una venta", description = "Actualiza los detalles de una venta existente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Venta actualizada con éxito"),
            @ApiResponse(responseCode = "404", description = "Venta no encontrada")
    })
    @PutMapping("/{id}")
    public EntityModel<Sale> updateSale(@RequestBody SaleActionRequest request, @PathVariable int id) {
        Sale updatedSale = saleService.updateSale(request.getSolicitante(), id, request.getSale());
        return assembler.toModel(updatedSale);
    }

    @Operation(summary = "Obtener todas las ventas de un cliente específico", description = "Devuelve una lista de ventas de un cliente.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ventas obtenidas con éxito"),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/cliente/{clientId}")
    public CollectionModel<EntityModel<Sale>> getSalesByClient(@PathVariable Integer clientId) {
        List<EntityModel<Sale>> sales = saleService.findByClientId(clientId).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(sales,
                linkTo(methodOn(SaleController.class).getSalesByClient(clientId)).withSelfRel());
    }
}
