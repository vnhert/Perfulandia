package com.perfulandia.perfulandia.Service;

import com.perfulandia.perfulandia.Model.Ship;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Repository.ShipRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShipServiceTest {
    @Mock
    private ShipRepository shipRepository;

    @InjectMocks
    private ShipService shipService;

    @Test
    void testGetShips() {
        User solicitante = new User();
        solicitante.setNombre("admin");

        when(shipRepository.findAll()).thenReturn(List.of(new Ship("Barco A", "Carga")));

        String result = shipService.getShips(solicitante);

        assertNotNull(result);
        assertTrue(result.contains("Barco A"));
        verify(shipRepository, times(1)).findAll();
    }

    @Test
    void testAddShip() {
        User solicitante = new User();
        solicitante.setNombre("admin");
        Ship ship = new Ship("Barco B", "Pasajeros");

        when(shipRepository.save(any(Ship.class))).thenReturn(ship);

        String result = shipService.addShip(solicitante, ship);

        assertNotNull(result);
        assertEquals("Barco agregado con éxito", result);
        verify(shipRepository, times(1)).save(ship);
    }

    @Test
    void testGetShip() {
        User solicitante = new User();
        solicitante.setNombre("admin");
        Ship ship = new Ship("Barco C", "Carga");

        when(shipRepository.findById(1)).thenReturn(Optional.of(ship));

        String result = shipService.getShip(solicitante, 1);

        assertNotNull(result);
        assertTrue(result.contains("Barco C"));
        verify(shipRepository, times(1)).findById(1);
    }

    @Test
    void testUpdateShip() {
        User solicitante = new User();
        solicitante.setNombre("admin");
        Ship existingShip = new Ship("Barco D", "Carga");
        Ship updatedShip = new Ship("Barco Actualizado", "Pasajeros");

        when(shipRepository.findById(1)).thenReturn(Optional.of(existingShip));
        when(shipRepository.save(any(Ship.class))).thenReturn(updatedShip);

        String result = shipService.updateShip(solicitante, 1, updatedShip);

        assertNotNull(result);
        assertEquals("Barco actualizado con éxito", result);
        verify(shipRepository, times(1)).save(existingShip);
    }

    @Test
    void testDeleteShip() {
        User solicitante = new User();
        solicitante.setNombre("admin");

        when(shipRepository.existsById(1)).thenReturn(true);

        String result = shipService.deleteShip(solicitante, 1);

        assertNotNull(result);
        assertEquals("Barco eliminado con éxito", result);
        verify(shipRepository, times(1)).deleteById(1);
    }

}
