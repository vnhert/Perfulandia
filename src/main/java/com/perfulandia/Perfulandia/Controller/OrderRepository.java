package com.perfulandia.Perfulandia.Controller;

import com.perfulandia.Perfulandia.Model.Order;
import com.perfulandia.Perfulandia.Model.Product;
import com.perfulandia.Perfulandia.Service.OrderService;
import com.perfulandia.Perfulandia.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public class OrderRepository {
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
   public String updateOrderState (@PathVariable int id, @RequestBody Order order) {return orderService.updateOrderState(id,order.getEstado());
    }
}
