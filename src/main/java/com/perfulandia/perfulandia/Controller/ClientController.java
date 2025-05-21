package com.perfulandia.perfulandia.Controller;


import com.perfulandia.perfulandia.Model.Client;
import com.perfulandia.perfulandia.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping()
    public String getClients(){
        return clientService.getClients();
    }

    @GetMapping("/{id}")
    public String getClient(@PathVariable int id){
        return clientService.getClient(id);
    }
    @PostMapping()
    public String addClient(@RequestBody Client client)
    {
        return clientService.addClient(client);
    }

    @DeleteMapping("/{id}")
    public String deleteClient(@PathVariable int id){
        return clientService.deleteClient(id);
    }

    @PutMapping("/{id}")
    public String updateClient(@RequestBody Client client){
        return clientService.updateClient(client);
    }
}
