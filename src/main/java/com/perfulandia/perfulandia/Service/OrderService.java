package com.perfulandia.perfulandia.Service;
import com.perfulandia.perfulandia.Model.Order;
import com.perfulandia.perfulandia.Model.Product;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service

public class OrderService {
    @Autowired
    private OrderRepository orderRepository;


    public String getOrders(User solicitante) {
        if (!solicitante.puedeGestionarPedidos()) {
            return "No tienes permiso para ver pedidos";
        }
        String output = "";
        for (Order order : orderRepository.findAll()) {
            output += "ID de pedido: " + order.getId() + "\n";
            output += "Estado del pedido: " + order.getEstado() + "\n";
            output += "Fecha de entrega: " + order.getFechaEntrega() + "\n";
            output += "Producto a abastecer: " + order.getProduct() + "\n";
            output += "Cantidad de producto: " + order.getCantidad() + "\n";
            output += "Sucursal asignada: " + order.getBranch() + "\n";
            output += "¿Autorizado?: " + (order.isAutorizado() ? "Sí" : "No") + "\n\n";
        }
        if (output.isEmpty()) {
            return "No hay pedidos registrados";
        } else {
            return output;
        }
    }

    public String addOrder(User solicitante, Order newOrder) {
        if (!solicitante.puedeGestionarPedidos()) {
            return "No tienes permiso para agregar pedidos";
        }
        orderRepository.save(newOrder);
        return "Pedido agregado con éxito";
    }

    public String getOrder(User solicitante, int id) {
        if (!solicitante.puedeGestionarPedidos()) {
            return "No tienes permiso para ver pedidos";
        }
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return "Pedido no encontrado";
        }
        String output = "";
        output += "ID de pedido: " + order.getId() + "\n";
        output += "Estado del pedido: " + order.getEstado() + "\n";
        output += "Fecha de entrega: " + order.getFechaEntrega() + "\n";
        output += "Producto a abastecer: " + order.getProduct() + "\n";
        output += "Cantidad de producto: " + order.getCantidad() + "\n";
        output += "Sucursal asignada: " + order.getBranch() + "\n";
        output += "¿Autorizado?: " + (order.isAutorizado() ? "Sí" : "No") + "\n";
        return output;
    }

    public String deleteOrder(User solicitante, int id) {
        if (!solicitante.puedeGestionarPedidos()) {
            return "No tienes permiso para eliminar pedidos";
        }
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return "Pedido eliminado con éxito";
        } else {
            return "Pedido no encontrado";
        }
    }

    public String updateOrder(User solicitante, int id, Order newOrder) {
        if (!solicitante.puedeGestionarPedidos()) {
            return "No tienes permiso para actualizar pedidos";
        }
        Order order = orderRepository.findById(id).orElse(null);
        if (order == null) {
            return "Pedido no encontrado";
        }
        order.setEstado(newOrder.getEstado());
        order.setFechaEntrega(newOrder.getFechaEntrega());
        order.setCantidad(newOrder.getCantidad());
        order.setBranch(newOrder.getBranch());
        order.setProduct(newOrder.getProduct());
        order.setAutorizado(newOrder.isAutorizado());
        orderRepository.save(order);
        return "Pedido actualizado con éxito";
    }

}
