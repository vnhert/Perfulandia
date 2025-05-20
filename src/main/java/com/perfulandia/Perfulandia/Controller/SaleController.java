package com.perfulandia.Perfulandia.Controller;
import com.perfulandia.Perfulandia.Model.Sale;
import com.perfulandia.Perfulandia.Service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping
    public String getOrders() {
        return saleService.getSales();
    }

    // Para obtener una orden específica, usando productId y fecha como identificadores
    @GetMapping("/{productId}/{fecha}")
    public String getOrder(@PathVariable int productId, @PathVariable String fecha) {
        return saleService.getSale(productId, fecha);
    }

    @PostMapping
    public String addOrder(@RequestBody Sale sale) {
        return saleService.addSale(sale);
    }

    @DeleteMapping("/{productId}/{fecha}")
    public String deleteOrder(@PathVariable int productId, @PathVariable String fecha) {
        return saleService.deleteSale(productId, fecha);
    }

    @PutMapping
    public String updateOrder(@RequestBody Sale sale) {
        return saleService.updateSale(sale);
    }
}
