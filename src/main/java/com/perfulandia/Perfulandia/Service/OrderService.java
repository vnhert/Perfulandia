package com.perfulandia.Perfulandia.Service;
import com.perfulandia.Perfulandia.Model.Order;
import com.perfulandia.Perfulandia.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service

public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    private List<Order> orders = new ArrayList<>();

    public String getOrders() {
        if (orders.isEmpty()) {
            return "No hay pedidos registrados";
        }

        String output = "";
        for (Order order : orders) {
            output += "ID Pedido: " + order.getId() + "\n";
            output += "Cliente: " + order.getClient().getNombre() + "\n";
            output += "Venta ID: " + order.getSale().getId() + "\n";
            output += "Estado: " + order.getEstado() + "\n\n";
        }

        return output;
    }

    public String getOrder(int id) {
        String output = "";
        for (Order order : orders) {
            if (order.getId() == id) {
                output += "ID Pedido: " + order.getId() + "\n";
                output += "Cliente: " + order.getClient().getNombre() + "\n";
                output += "Venta ID: " + order.getSale().getId() + "\n";
                output += "Estado: " + order.getEstado();
            }
        }

        if (output.isEmpty()) {
            return "Pedido no encontrado";
        } else {
            return output;
        }
    }

    public String addOrder(Order order) {
        orders.add(order);
        return "Pedido agregado con éxito";
    }

    public String deleteOrder(int id) {
        for (Order order : orders) {
            if (order.getId() == id) {
                orders.remove(order);
                return "Pedido eliminado con éxito";
            }
        }
        return "Pedido no encontrado";
    }

    public String updateOrderState(int id,String nuevoEstado) {
        for (Order order : orders) {
            if (order.getId() == id) {
                order.setEstado(nuevoEstado);
                return "Estado del pedido actualizado con éxito";
            }
        }
        return "Pedido no encontrado";
    }
}
