package com.perfulandia.perfulandia.Controller;


import com.perfulandia.perfulandia.Model.Ship;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/envios")
public class ShipController {
    @Autowired
    private ShipService shipService;

    // Ejemplo: pasar el usuario como parámetro (ajusta según tu autenticación real)
    @GetMapping
    public String getShips(@RequestBody User solicitante) {
        return shipService.getShips(solicitante);
    }

    @GetMapping("/{id}")
    public String getShip(@RequestBody User solicitante, @PathVariable int id) {
        return shipService.getShip(solicitante, id);
    }

    @PostMapping
    public String addShip(@RequestBody User solicitante, @RequestBody Ship newShip) {
        return shipService.addShip(solicitante, newShip);
    }

    @PutMapping("/{id}")
    public String updateShip(@RequestBody User solicitante, @PathVariable int id, @RequestBody Ship newShip) {
        return shipService.updateShip(solicitante, id, newShip);
    }

    @DeleteMapping("/{id}")
    public String deleteShip(@RequestBody User solicitante, @PathVariable int id) {
        return shipService.deleteShip(solicitante, id);
    }
}
