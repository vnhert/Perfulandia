package com.perfulandia.perfulandia.Controller;


import com.perfulandia.perfulandia.Model.Client;
import com.perfulandia.perfulandia.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping("/clients")
    public String getUsers(){
        return clientService.getAllClients();
    }

    @GetMapping("/clients/{id}")
    public String getClientById(@PathVariable int id){
        return clientService.getClient(id);
    }
    @PostMapping("/clients")
    public String addUser(@RequestBody Client client)
    {
        return clientService.saveClient(client);
    }

    @DeleteMapping("/clients/{id}")
    public String deleteClient(@PathVariable int id){
        return clientService.deleteClient(id);
    }

    @PutMapping("/clients")
    public String updateClient(@RequestBody Client user){
        return clientService.updateUser(user);
    }
}
