package com.perfulandia.perfulandia.Service;
import com.perfulandia.perfulandia.Model.Client;
import com.perfulandia.perfulandia.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public String getClients() {
        StringBuilder output = new StringBuilder();
        for (Client client : clientRepository.findAll()) {
            output.append("ID Cliente: ").append(client.getId()).append("\n");
            output.append("Nombre Cliente: ").append(client.getNombre()).append("\n");
            output.append("Correo: ").append(client.getCorreo()).append("\n");

        }
        return output.isEmpty() ? "No se encontraron clientes" : output.toString();
    }

    public String getClient(int id) {
        String output = "";
        for (Client client : clientRepository.findAll()) {
            if (client.getId() == id) {
                output += "ID Cliente: " + client.getId() + "\n";
                output += "Nombre Cliente: " + client.getNombre() + "\n";
                output += "Correo: " + client.getCorreo() + "\n";
            }
        }
        return output.isEmpty() ? "No se encontró al cliente" : output;
    }

    public String addClient(Client client) {
        clientRepository.save(client);
        return "Cliente agregado exitosamente";
    }

    public String deleteClient(int id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return "Cliente eliminado exitosamente";
        } else {
            return "No se encontró al cliente";
        }
    }

    public String updateClient(int id, Client updatedClient) {
        if (clientRepository.existsById(id)) {
            Client client = clientRepository.findById(id).orElse(null);
            if (client != null) {
                client.setNombre(updatedClient.getNombre());
                client.setCorreo(updatedClient.getCorreo());
                clientRepository.save(client);
                return "Cliente actualizado exitosamente";
            }
        }
        return "No se encontró al cliente";
    }
}
