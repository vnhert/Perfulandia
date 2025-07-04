package com.perfulandia.perfulandia.Controller;


import com.perfulandia.perfulandia.Model.Ship;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.perfulandia.perfulandia.Model.CrearEnvioRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "Envíos", description = "Operaciones relacionadas con los envíos")
@RestController
@RequestMapping("/envios")
    public class ShipController {
        @Autowired
        private ShipService shipService;

    @Operation(summary = "Crear un nuevo envio")
        @PostMapping("/crear")
        public String crearEnvio(@RequestBody CrearEnvioRequest request) {
        return shipService.crearEnvio(request.solicitante, request.cliente, request.productos, request.cantidades, request.cupon);
    }
    @Operation(summary = "Obtener todos los envíos")
        @GetMapping
        public String getShips(@RequestBody User solicitante) {
            return shipService.getShips(solicitante);
        }

    @Operation(summary = "Obtener un envío por ID")
        @GetMapping("/{id}")
        public String getShip(@RequestBody User solicitante, @PathVariable int id) {
            return shipService.getShip(solicitante, id);
        }
    @Operation(summary = "Registrar un nuevo envío")
        @PostMapping
        public String addShip(@RequestBody User solicitante, @RequestBody Ship newShip) {
            return shipService.addShip(solicitante, newShip);
        }
    @Operation(summary = "Actualizar un envío")
        @PutMapping("/{id}")
        public String updateShip(@RequestBody User solicitante, @PathVariable int id, @RequestBody Ship newShip) {
            return shipService.updateShip(solicitante, id, newShip);
        }
    @Operation(summary = "Eliminar un envío")
        @DeleteMapping("/{id}")
        public String deleteShip(@RequestBody User solicitante, @PathVariable int id) {
            return shipService.deleteShip(solicitante, id);
        }
}

