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
            output.append("Email: ").append(client.getEmail()).append("\n");
            output.append("Dirección: ").append(client.getDireccion()).append("\n");
            output.append("Teléfono: ").append(client.getTelefono()).append("\n\n");
        }
        return output.isEmpty() ? "No se encontraron clientes" : output.toString();
    }

    public String getClient(int id) {
        return clientRepository.findById(id)
                .map(client -> "ID Cliente: " + client.getId() + "\n"
                        + "Nombre Cliente: " + client.getNombre() + "\n"
                        + "Email: " + client.getEmail() + "\n"
                        + "Dirección: " + client.getDireccion() + "\n"
                        + "Teléfono: " + client.getTelefono())
                .orElse("No se encontró al cliente");
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

    public String updateClient(Client updatedClient) {
        if (clientRepository.existsById(updatedClient.getId())) {
            clientRepository.save(updatedClient);
            return "Cliente actualizado exitosamente";
        } else {
            return "No se encontró al cliente";
        }
    }
}
