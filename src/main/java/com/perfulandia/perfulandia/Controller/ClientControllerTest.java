package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Model.Client;
import com.perfulandia.perfulandia.Service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
class ClientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Test
    void testGetAllClients() throws Exception {
        when(clientService.getClients()).thenReturn(List.of(new Client("Cliente 1", "cliente1@mail.com")));

        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Cliente 1"));
    }

    @Test
    void testGetClientById() throws Exception {
        when(clientService.getClient(1)).thenReturn(new Client("Cliente 1", "cliente1@mail.com"));

        mockMvc.perform(get("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Cliente 1"));
    }

    @Test
    void testAddClient() throws Exception {
        when(clientService.addClient(any(Client.class))).thenReturn("Cliente agregado con éxito");

        mockMvc.perform(post("/clientes")
                        .contentType("application/json")
                        .content("{\"nombre\":\"Cliente 2\",\"correo\":\"cliente2@mail.com\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Cliente agregado con éxito"));
    }

    @Test
    void testUpdateClient() throws Exception {
        when(clientService.updateClient(1, any(Client.class))).thenReturn("Cliente actualizado con éxito");

        mockMvc.perform(put("/clientes/1")
                        .contentType("application/json")
                        .content("{\"nombre\":\"Cliente Actualizado\",\"correo\":\"actualizado@mail.com\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Cliente actualizado con éxito"));
    }

    @Test
    void testDeleteClient() throws Exception {
        when(clientService.deleteClient(1)).thenReturn("Cliente eliminado con éxito");

        mockMvc.perform(delete("/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Cliente eliminado con éxito"));
    }
}