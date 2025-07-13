package com.perfulandia.perfulandia.Service;

import com.perfulandia.perfulandia.Model.Client;
import com.perfulandia.perfulandia.Repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional; // Import Optional

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    // Modified to return List<Client> instead of String
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    // Modified to return Client instead of String, throws ClientNotFoundException
    public Client getClient(int id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Cliente no encontrado con ID: " + id));
    }

    // Modified to return the saved Client instead of String
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    // Modified to return void and throw ClientNotFoundException if not found
    public void deleteClient(int id) {
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException("Cliente no encontrado con ID: " + id);
        }
        clientRepository.deleteById(id);
    }

    // Modified to return the updated Client, throws ClientNotFoundException if not found
    public Client updateClient(int id, Client updatedClient) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Cliente no encontrado con ID: " + id));

        // Update properties
        client.setNombre(updatedClient.getNombre());
        client.setCorreo(updatedClient.getCorreo());
        // Add other properties to update as needed, e.g., client.setContrasena(updatedClient.getContrasena());

        return clientRepository.save(client);
    }
}