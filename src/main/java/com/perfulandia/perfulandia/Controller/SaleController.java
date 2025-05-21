package com.perfulandia.perfulandia.Controller;
import com.perfulandia.perfulandia.Model.SaleActionRequest;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ventas")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping
    public String getSales(@RequestBody User solicitante) {
        return saleService.getSales(solicitante);
    }

    @GetMapping("/{id}")
    public String getSale(@RequestBody User solicitante, @PathVariable int id) {
        return saleService.getSale(solicitante, id);
    }

    @PostMapping
    public String addSale(@RequestBody SaleActionRequest request) {
        return saleService.addSale(request.getSolicitante(), request.getSale());
    }

    @DeleteMapping("/{id}")
    public String deleteSale(@RequestBody User solicitante, @PathVariable int id) {
        return saleService.deleteSale(solicitante, id);
    }

    @PutMapping("/{id}")
    public String updateSale(@RequestBody SaleActionRequest request, @PathVariable int id) {
        return saleService.updateSale(request.getSolicitante(), id, request.getSale());
    }
}
