package com.perfulandia.perfulandia.Service;
import com.perfulandia.perfulandia.Model.Client;
import com.perfulandia.perfulandia.Repository.ClientRepository;
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
        return clientRepository.getClient(id);
    }
    public String deleteClient(int id) {
        return clientRepository.deleteClient(id);
    }
    public String getAllClients() {
        return clientRepository.getClients();
    }
    public String updateUser(Client user) {
        return clientRepository.updateClient(user);
    }
}
