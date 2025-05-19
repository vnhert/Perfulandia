package com.perfulandia.perfulandia.service;
import com.perfulandia.perfulandia.model.Client;
import com.perfulandia.perfulandia.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public String saveClient(Client client) {
        return clientRepository.addClient(client);
    }

    public String getClient(int id) {
        return ClientRepository.getClient(id);
    }
    public String deleteClient(int id) {
        return userRepository.deleteClient(id);
    }
    public String getAllClients() {
        return clientRepository.getClients();
    }
    public String updateUser(Client user) {
        return clientRepository.updateUser(user);
    }
}
