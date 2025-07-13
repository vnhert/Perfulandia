package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Model.Ship;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Model.CrearEnvioRequest;
import com.perfulandia.perfulandia.Service.ShipService;
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

@Tag(name = "Envíos", description = "Operaciones relacionadas con los envíos")
@RestController
@RequestMapping("/envios")
public class ShipController {

    @Autowired
    private ShipService shipService;

    @Operation(summary = "Crear un nuevo envío")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Envío creado con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping("/crear")
    public EntityModel<Ship> crearEnvio(@RequestBody CrearEnvioRequest request) {
        Ship newShip = shipService.crearEnvio(request.getSolicitante(), request.getCliente(), request.getProductos(), request.getCantidades(), request.getCupon());
        return EntityModel.of(newShip,
                linkTo(methodOn(ShipController.class).getShip(request.getSolicitante(), newShip.getId())).withSelfRel(),
                linkTo(methodOn(ShipController.class).getShips(request.getSolicitante())).withRel("envios"));
    }

    @Operation(summary = "Obtener todos los envíos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista obtenida con éxito"),
            @ApiResponse(responseCode = "403", description = "No autorizado")
    })
    @GetMapping
    public CollectionModel<EntityModel<Ship>> getShips(@RequestBody User solicitante) {
        List<EntityModel<Ship>> ships = shipService.getShips(solicitante).stream()
                .map(ship -> EntityModel.of(ship,
                        linkTo(methodOn(ShipController.class).getShip(solicitante, ship.getId())).withSelfRel(),
                        linkTo(methodOn(ShipController.class).getShips(solicitante)).withRel("envios")))
                .collect(Collectors.toList());

        return CollectionModel.of(ships,
                linkTo(methodOn(ShipController.class).getShips(solicitante)).withSelfRel());
    }

    @Operation(summary = "Obtener un envío por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Envío obtenido con éxito"),
            @ApiResponse(responseCode = "404", description = "Envío no encontrado")
    })
    @GetMapping("/{id}")
    public EntityModel<Ship> getShip(@RequestBody User solicitante, @PathVariable int id) {
        Ship ship = shipService.getShip(solicitante, id);
        return EntityModel.of(ship,
                linkTo(methodOn(ShipController.class).getShip(solicitante, id)).withSelfRel(),
                linkTo(methodOn(ShipController.class).getShips(solicitante)).withRel("envios"));
    }

    @Operation(summary = "Registrar un nuevo envío")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Envío registrado con éxito"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    @PostMapping
    public EntityModel<Ship> addShip(@RequestBody User solicitante, @RequestBody Ship newShip) {
        Ship ship = shipService.addShip(solicitante, newShip);
        return EntityModel.of(ship,
                linkTo(methodOn(ShipController.class).getShip(solicitante, ship.getId())).withSelfRel(),
                linkTo(methodOn(ShipController.class).getShips(solicitante)).withRel("envios"));
    }

    @Operation(summary = "Actualizar un envío")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Envío actualizado con éxito"),
            @ApiResponse(responseCode = "404", description = "Envío no encontrado")
    })
    @PutMapping("/{id}")
    public EntityModel<Ship> updateShip(@RequestBody User solicitante, @PathVariable int id, @RequestBody Ship newShip) {
        Ship updatedShip = shipService.updateShip(solicitante, id, newShip);
        return EntityModel.of(updatedShip,
                linkTo(methodOn(ShipController.class).getShip(solicitante, id)).withSelfRel(),
                linkTo(methodOn(ShipController.class).getShips(solicitante)).withRel("envios"));
    }

    @Operation(summary = "Eliminar un envío")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Envío eliminado con éxito"),
            @ApiResponse(responseCode = "404", description = "Envío no encontrado")
    })
    @DeleteMapping("/{id}")
    public String deleteShip(@RequestBody User solicitante, @PathVariable int id) {
        return shipService.deleteShip(solicitante, id);
    }
}

