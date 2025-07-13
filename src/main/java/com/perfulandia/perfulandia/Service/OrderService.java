package com.perfulandia.perfulandia.Service;

import com.perfulandia.perfulandia.Model.Order;
import com.perfulandia.perfulandia.Model.User; // Still needed for other methods like addOrder
import com.perfulandia.perfulandia.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional; // Ensure this is imported

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    // Corrected getOrders method:
    // It should NOT take a User parameter if the controller is not passing one
    // Authorization should be handled by a security layer (e.g., Spring Security)
    // or by obtaining the user from a security context within the service.
    public List<Order> getOrders() { // REMOVED 'User solicitante' parameter
        // IMPORTANT: If you still need user authorization, you'd get the current user
        // from Spring Security's SecurityContextHolder here. For example:
        // User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // if (!currentUser.puedeGestionarPedidos()) {
        //     throw new UnauthorizedException("No tienes permiso para ver pedidos");
        // }
        return orderRepository.findAll();
    }

    // Corrected getOrder method:
    // It should ONLY take 'int id' as a parameter if the controller is only passing 'id'
    // Authorization should be handled by a security layer or internally.
    public Order getOrder(int id) { // REMOVED 'User solicitante' parameter
        // Same authorization consideration as getOrders()
        // User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // if (!currentUser.puedeGestionarPedidos()) {
        //     throw new UnauthorizedException("No tienes permiso para ver pedidos");
        // }
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Pedido no encontrado con ID: " + id));
    }

    // Keep the following methods as they are (they correctly take User solicitante via @RequestBody in controller)

    public Order addOrder(User solicitante, Order newOrder) {
        if (!solicitante.puedeGestionarPedidos()) {
            throw new UnauthorizedException("No tienes permiso para agregar pedidos");
        }
        return orderRepository.save(newOrder);
    }

    public void deleteOrder(User solicitante, int id) {
        if (!solicitante.puedeGestionarPedidos()) {
            throw new UnauthorizedException("No tienes permiso para eliminar pedidos");
        }
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Pedido no encontrado con ID: " + id);
        }
        orderRepository.deleteById(id);
    }

    public Order updateOrder(User solicitante, int id, Order updatedOrderData) {
        if (!solicitante.puedeGestionarPedidos()) {
            throw new UnauthorizedException("No tienes permiso para actualizar pedidos");
        }
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Pedido no encontrado con ID: " + id));

        order.setEstado(updatedOrderData.getEstado());
        order.setFechaEntrega(updatedOrderData.getFechaEntrega());
        order.setCantidad(updatedOrderData.getCantidad());
        order.setBranch(updatedOrderData.getBranch());
        order.setProduct(updatedOrderData.getProduct());
        order.setAutorizado(updatedOrderData.isAutorizado());

        return orderRepository.save(order);
    }

    public Order updateStateOrder(User solicitante, int id, Order updatedOrderData) {
        if (!solicitante.puedeActualizarPedido()) { // Make sure 'puedeActualizarPedido' exists in User
            throw new UnauthorizedException("No tienes permiso para actualizar estado de pedidos");
        }
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Pedido no encontrado con ID: " + id));

        order.setEstado(updatedOrderData.getEstado());
        return orderRepository.save(order);
    }

    public List<Order> findByEstado(String estado) {
        return orderRepository.findByEstado(estado);
    }
}