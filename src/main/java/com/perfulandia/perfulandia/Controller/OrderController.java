package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Model.Order;
import com.perfulandia.perfulandia.Model.OrderActionRequest;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pedidos")

public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public String getOrders(@RequestBody User solicitante) {
        return orderService.getOrders(solicitante);
    }

    @GetMapping("/{id}")
    public String getOrder(@RequestBody User solicitante, @PathVariable int id) {
        return orderService.getOrder(solicitante, id);
    }

    @PostMapping
    public String addOrder(@RequestBody OrderActionRequest request) {
        return orderService.addOrder(request.getSolicitante(), request.getOrder());
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@RequestBody User solicitante, @PathVariable int id) {
        return orderService.deleteOrder(solicitante, id);
    }

    @PutMapping("/{id}")
    public String updateOrder(@RequestBody OrderActionRequest request, @PathVariable int id) {
        return orderService.updateOrder(request.getSolicitante(), id, request.getOrder());
    }

    @PutMapping("/{id}/estado")
    public String updateStateOrder(@RequestBody OrderActionRequest request, @PathVariable int id) {
        return orderService.updateStateOrder(request.getSolicitante(), id, request.getOrder());
    }
}
