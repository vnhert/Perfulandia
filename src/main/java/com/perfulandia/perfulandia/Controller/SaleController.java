package com.perfulandia.perfulandia.Controller;
import com.perfulandia.perfulandia.Assembler.SaleAssembler;
import com.perfulandia.perfulandia.Model.SaleActionRequest;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;

import java.util.List;
import java.util.stream.Collectors;
@Tag(name = "Ventas", description = "Operaciones relacionadas con las ventas")
@RestController
@RequestMapping("/ventas")
public class SaleController {
    @Autowired
    private SaleService saleService;
    @Autowired

    private SaleAssembler assembler;


    @Operation(summary = "Obtener todas las ventas")
    @GetMapping

    public CollectionModel<EntityModel<Sale>> getSales() {
        List<EntityModel<Sale>> sales = saleService.getSales().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(sales);
    }


    @Operation(summary = "Obtener una venta por ID")
    @GetMapping("/{id}")
    public EntityModel<Sale> getSale(@PathVariable int id) {
        Sale sale = saleService.getSale(id);
        return assembler.toModel(sale);
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

    @Operation(summary = "Obtener todas las ventas de un cliente específico")
    @GetMapping("/cliente/{clientId}")
    public CollectionModel<EntityModel<Sale>> getSalesByClient(@PathVariable Integer clientId) {
        List<EntityModel<Sale>> sales = saleService.findByClientId(clientId).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(sales,
                linkTo(methodOn(SaleController.class).getSalesByClient(clientId)).withSelfRel());
    }
}
