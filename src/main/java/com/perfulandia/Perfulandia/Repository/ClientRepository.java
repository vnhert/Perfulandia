package com.perfulandia.Perfulandia.Repository;
import org.springframework.stereotype.Repository;

import com.perfulandia.Perfulandia.Model.Client;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientRepository {
    private List<Client> clients = new ArrayList<>();

    public ClientRepository() {

    }

    public String getClients() {
        if (clients.isEmpty()) {
            return "No se encontraron clientes";
        }
        StringBuilder output = new StringBuilder();
        for (Client client : clients) {
            output.append("ID Cliente: ").append(client.getId()).append("\n");
            output.append("Nombre Cliente: ").append(client.getNombre()).append("\n");
            output.append("Email: ").append(client.getEmail()).append("\n");
            output.append("Dirección: ").append(client.getDireccion()).append("\n");
            output.append("Teléfono: ").append(client.getTelefono()).append("\n\n");
        }
        return output.toString();
    }

    public String getClient(int id) {
        for (Client client : clients) {
            if (client.getId() == id) {
                return "ID Cliente: " + client.getId() + "\n" +
                        "Nombre Cliente: " + client.getNombre() + "\n" +
                        "Email: " + client.getEmail() + "\n" +
                        "Dirección: " + client.getDireccion() + "\n" +
                        "Teléfono: " + client.getTelefono();
            }
        }
        return "No se encontró al cliente";
    }

    public String addClient(Client client) {
        clients.add(client);
        return "Cliente agregado exitosamente";
    }

    public String deleteClient(int id) {
        for (Client client : clients) {
            if (client.getId() == id) {
                clients.remove(client);
                return "Cliente eliminado exitosamente";
            }
        }
        return "No se encontró al cliente";
    }

    public String updateClient(Client client) {
        int index = -1;
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getId() == client.getId()) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return "No se encontró al cliente";
        } else {
            clients.set(index, client);
            return "Cliente actualizado exitosamente";
        }
    }
}
