package com.perfulandia.perfulandia.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public String saveClient(Client client) {
        return userRepository.addClient(client);
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
