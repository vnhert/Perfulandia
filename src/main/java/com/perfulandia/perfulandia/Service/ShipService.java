package com.perfulandia.perfulandia.Service;

import com.perfulandia.perfulandia.Model.Ship;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Repository.ShipRepository;
import org.springframework.stereotype.Service;

@Service
public class ShipService {

    private ShipRepository shipRepository;

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
}
