package com.perfulandia.perfulandia.Service;
import com.perfulandia.perfulandia.Model.Client;
import com.perfulandia.perfulandia.Repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    void testGetClients() {
        Client client = new Client();
        client.setId(1);
        client.setNombre("Juan Pérez");

        when(clientRepository.findAll()).thenReturn(List.of(client));

        List<Client> result = clientService.getClients();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Juan Pérez", result.get(0).getNombre());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void testGetClient() {
        Client client = new Client();
        client.setId(1);
        client.setNombre("Juan Pérez");

        when(clientRepository.findById(1)).thenReturn(Optional.of(client));

        Client result = clientService.getClient(1);

        assertNotNull(result);
        assertEquals("Juan Pérez", result.getNombre());
        verify(clientRepository, times(1)).findById(1);
    }

    @Test
    void testAddClient() {
        Client client = new Client();
        client.setNombre("Juan Pérez");

        when(clientRepository.save(any(Client.class))).thenReturn(client);

        String result = clientService.addClient(client);

        assertNotNull(result);
        assertEquals("Cliente agregado con éxito", result);
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void testUpdateClient() {
        Client existingClient = new Client();
        existingClient.setId(1);
        existingClient.setNombre("Juan Pérez");

        Client updatedClient = new Client();
        updatedClient.setNombre("Juan Actualizado");

        when(clientRepository.findById(1)).thenReturn(Optional.of(existingClient));
        when(clientRepository.save(any(Client.class))).thenReturn(updatedClient);

        String result = clientService.updateClient(1, updatedClient);

        assertNotNull(result);
        assertEquals("Cliente actualizado con éxito", result);
        verify(clientRepository, times(1)).save(existingClient);
    }

    @Test
    void testDeleteClient() {
        when(clientRepository.existsById(1)).thenReturn(true);

        String result = clientService.deleteClient(1);

        assertNotNull(result);
        assertEquals("Cliente eliminado con éxito", result);
        verify(clientRepository, times(1)).deleteById(1);
    } @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    void testGetClients() {
        Client client = new Client();
        client.setId(1);
        client.setNombre("Juan Pérez");

        when(clientRepository.findAll()).thenReturn(List.of(client));

        List<Client> result = clientService.getClients();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Juan Pérez", result.get(0).getNombre());
        verify(clientRepository, times(1)).findAll();
    }

    @Test
    void testGetClient() {
        Client client = new Client();
        client.setId(1);
        client.setNombre("Juan Pérez");

        when(clientRepository.findById(1)).thenReturn(Optional.of(client));

        Client result = clientService.getClient(1);

        assertNotNull(result);
        assertEquals("Juan Pérez", result.getNombre());
        verify(clientRepository, times(1)).findById(1);
    }

    @Test
    void testAddClient() {
        Client client = new Client();
        client.setNombre("Juan Pérez");

        when(clientRepository.save(any(Client.class))).thenReturn(client);

        String result = clientService.addClient(client);

        assertNotNull(result);
        assertEquals("Cliente agregado con éxito", result);
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void testUpdateClient() {
        Client existingClient = new Client();
        existingClient.setId(1);
        existingClient.setNombre("Juan Pérez");

        Client updatedClient = new Client();
        updatedClient.setNombre("Juan Actualizado");

        when(clientRepository.findById(1)).thenReturn(Optional.of(existingClient));
        when(clientRepository.save(any(Client.class))).thenReturn(updatedClient);

        String result = clientService.updateClient(1, updatedClient);

        assertNotNull(result);
        assertEquals("Cliente actualizado con éxito", result);
        verify(clientRepository, times(1)).save(existingClient);
    }

    @Test
    void testDeleteClient() {
        when(clientRepository.existsById(1)).thenReturn(true);

        String result = clientService.deleteClient(1);

        assertNotNull(result);
        assertEquals("Cliente eliminado con éxito", result);
        verify(clientRepository, times(1)).deleteById(1);
    }
}
