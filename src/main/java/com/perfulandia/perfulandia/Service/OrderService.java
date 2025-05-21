package com.perfulandia.perfulandia.Service;
import com.perfulandia.perfulandia.Model.Order;
import com.perfulandia.perfulandia.Model.Product;
import com.perfulandia.perfulandia.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service

public class OrderService {
    @Autowired
    private OrderRepository orderRepository;


    public String getOrders() {
        String output = "";
        for (Order order : orderRepository.findAll()) {
            output += "ID de pedido: " + order.getId() + "\n";
            output += "Estado del pedido: " + order.getEstado() + "\n";
            output += "Fecha de entrega: " + order.getFechaEntrega() + "\n";
            output += "Metodo de envio: " + order.getMetodoEnvio() + "\n";
            output += "Costo de envio: " + order.getCosto() + "\n\n";
        }

        if (output.isEmpty()) {
            return "No hay pedidos registrados";
        } else {
            return output;
        }
    }

    public String addOrder(Order newOrder) {
        orderRepository.save(newOrder);
        return "Pedido agregado con éxito";
    }

    public String getOrder(int id) {
        String output = "";
        for (Order order : orderRepository.findAll()) {
            if (order.getId() == id) {
                output += "ID de pedido: " + order.getId() + "\n";
                output += "Estado del pedido: " + order.getEstado() + "\n";
                output += "Fecha de entrega: " + order.getFechaEntrega() + "\n";
                output += "Metodo de envio: " + order.getMetodoEnvio() + "\n";
                output += "Costo de envio: " + order.getCosto() + "\n\n";
            }
        }

        if (output.isEmpty()) {
            return "Pedido no encontrado";
        } else {
            return output;
        }
    }

    public String deleteOrder(int id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return "Pedido eliminado con éxito";
        } else {
            return "Pedido no encontrado";
        }
    }

    public String updateOrder(int id, Order newOrder) {
        if (orderRepository.existsById(id)) {
            for (Order order : orderRepository.findAll()) {
                if (order.getId() == id) {
                    order.setEstado(newOrder.getEstado());
                    order.setFechaEntrega(newOrder.getFechaEntrega());
                    order.setMetodoEnvio(newOrder.getMetodoEnvio());
                    order.setCosto(newOrder.getCosto());

                }
            }
            return "Pedido actualizado con éxito";
        } else {
            return "Pedido no encontrado";
        }
    }

}
