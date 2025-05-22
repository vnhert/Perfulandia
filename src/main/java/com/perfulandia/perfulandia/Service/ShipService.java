package com.perfulandia.perfulandia.Service;

import com.perfulandia.perfulandia.Model.*;
import com.perfulandia.perfulandia.Repository.ShipProductRepository;
import com.perfulandia.perfulandia.Repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShipService {

    @Autowired
    private ShipRepository shipRepository;
    @Autowired
    private ShipProductRepository shipProductRepository;

    public String getShips(User solicitante) {
        if (!solicitante.puedeGestionarEnvios()) {
            return "No tienes permiso para ver envíos";
        }
        StringBuilder output = new StringBuilder();
        for (Ship ship : shipRepository.findAll()) {
            output.append("ID Envío: ").append(ship.getId()).append("\n");
            output.append("Estado: ").append(ship.getEstado()).append("\n");
            output.append("Fecha de entrega: ").append(ship.getFechaEntrega()).append("\n");
            output.append("Cliente: ").append(ship.getCliente() != null ? ship.getCliente().getNombre() : "Sin cliente").append("\n\n");
        }
        return output.length() == 0 ? "No hay envíos registrados" : output.toString();
    }

    public String addShip(User solicitante, Ship newShip) {
        if (!solicitante.puedeGestionarEnvios()) {
            return "No tienes permiso para agregar envíos";
        }
        shipRepository.save(newShip);
        return "Envío agregado con éxito";
    }

    public String getShip(User solicitante, int id) {
        if (!solicitante.puedeGestionarEnvios()) {
            return "No tienes permiso para ver envíos";
        }
        Ship ship = shipRepository.findById(id).orElse(null);
        if (ship == null) {
            return "Envío no encontrado";
        }
        StringBuilder output = new StringBuilder();
        output.append("ID Envío: ").append(ship.getId()).append("\n");
        output.append("Estado: ").append(ship.getEstado()).append("\n");
        output.append("Fecha de entrega: ").append(ship.getFechaEntrega()).append("\n");
        output.append("Cliente: ").append(ship.getCliente() != null ? ship.getCliente().getNombre() : "Sin cliente").append("\n");
        return output.toString();
    }

    public String deleteShip(User solicitante, int id) {
        if (!solicitante.puedeGestionarEnvios()) {
            return "No tienes permiso para eliminar envíos";
        }
        if (shipRepository.existsById(id)) {
            shipRepository.deleteById(id);
            return "Envío eliminado con éxito";
        } else {
            return "Envío no encontrado";
        }
    }

    public String updateShip(User solicitante, int id, Ship newShip) {
        if (!solicitante.puedeGestionarEnvios()) {
            return "No tienes permiso para actualizar envíos";
        }
        Ship ship = shipRepository.findById(id).orElse(null);
        if (ship == null) {
            return "Envío no encontrado";
        }
        ship.setEstado(newShip.getEstado());
        ship.setFechaEntrega(newShip.getFechaEntrega());
        ship.setCliente(newShip.getCliente());
        shipRepository.save(ship);
        return "Envío actualizado con éxito";
    }

    public String crearEnvio(User solicitante, Client cliente, List<Product> productos, List<Integer> cantidades) {
        if (!solicitante.puedeCrearEnvio()) {
            return "No tienes permiso para crear envíos";
        }
        if (productos.size() != cantidades.size()) {
            return "La cantidad de productos y cantidades no coincide";
        }

        Ship envio = new Ship();
        envio.setCliente(cliente);
        envio.setEstado("Pendiente");
        envio.setFechaEntrega(null);
        envio = shipRepository.save(envio);

        List<ShipProduct> shipProducts = new ArrayList<>();
        for (int i = 0; i < productos.size(); i++) {
            ShipProduct sp = new ShipProduct();
            sp.setShip(envio);
            sp.setProduct(productos.get(i));
            sp.setCantidad(cantidades.get(i));
            shipProductRepository.save(sp);
            shipProducts.add(sp);
        }
        envio.setProductos(shipProducts);
        shipRepository.save(envio);

        return "Envío creado con éxito";
    }


}
