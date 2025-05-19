package com.perfulandia.perfulandia.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {
    @Autowired
    Clientservice clientService;

    @GetMapping("/users")
    public String getUsers(){
        return clientService.getAllClients();
    }

    @GetMapping("/users/{id}")
    public String getClientById(@PathVariable int id){
        return clientService.getClient(id);
    }
    @PostMapping("/users")
    public String addUser(@RequestBody Client user){
        return clientService.saveClient(client);
    }

    @DeleteMapping("/users/{id}")
    public String deleteClient(@PathVariable int id){
        return clientService.deleteClient(id);
    }

    @PutMapping("/users")
    public String updateClient(@RequestBody Client user){
        return clientService.updateClient(user);
    }
}
