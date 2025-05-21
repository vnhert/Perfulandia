package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Model.Order;
import com.perfulandia.perfulandia.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public String getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/{id}")
    public String getOrder(@PathVariable int id) {return orderService.getOrder(id);
    }

    @PostMapping
    public String addOrder(@RequestBody Order order) {
        return orderService.addOrder(order);
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable int id) {
        return orderService.deleteOrder(id);
    }

    @PutMapping("/{id}")
   public String updateOrder (@PathVariable int id, @RequestBody Order order) {return orderService.updateOrder(id,order);
    }
}
