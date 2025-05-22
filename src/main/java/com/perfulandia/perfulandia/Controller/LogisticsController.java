package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Model.Logistics;
import com.perfulandia.perfulandia.Service.LogisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/logisticas")
public class LogisticsController {
    @Autowired
     LogisticsService logisticsService;

    @GetMapping
    public List<Logistics> getAllLogistics() {
        return logisticsService.getAllLogistics();
    }

    @GetMapping("/{id}")
    public Optional<Logistics> getLogisticsById(@PathVariable int id) {
        return logisticsService.getLogisticsById(id);
    }

    @PostMapping
    public Logistics addLogistics(@RequestBody Logistics logistics) {
        return logisticsService.saveLogistics(logistics);
    }

    @DeleteMapping("/{id}")
    public void deleteLogistics(@PathVariable int id) {
        logisticsService.deleteLogistics(id);
    }
}
