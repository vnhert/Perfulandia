package com.perfulandia.perfulandia;
import com.perfulandia.perfulandia.Model.Proveedor;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Repository.ProveedorRepository;
import com.perfulandia.perfulandia.Service.ProveedorService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProveedorServiceTest {
    @Mock
    private ProveedorRepository proveedorRepository;

    @InjectMocks
    private ProveedorService proveedorService;

    @Test
    void testGetProviders() {
        User solicitante = new User();
        solicitante.setNombre("admin");
        when(solicitante.puedeGestionarProveedores()).thenReturn(true);

        when(proveedorRepository.findAll()).thenReturn(List.of(new Proveedor()));

        String result = proveedorService.getProviders(solicitante);

        assertNotNull(result);
        assertTrue(result.contains("ID del proveedor"));
        verify(proveedorRepository, times(1)).findAll();
    }

    @Test
    void testAddProvider() {
        User solicitante = new User();
        solicitante.setNombre("admin");
        when(solicitante.puedeGestionarProveedores()).thenReturn(true);

        Proveedor proveedor = new Proveedor();
        when(proveedorRepository.save(proveedor)).thenReturn(proveedor);

        String result = proveedorService.addProvider(solicitante, proveedor);

        assertEquals("Proveedor agregado con éxito", result);
        verify(proveedorRepository, times(1)).save(proveedor);
    }

    @Test
    void testGetProvider() {
        User solicitante = new User();
        solicitante.setNombre("admin");
        when(solicitante.puedeGestionarProveedores()).thenReturn(true);

        Proveedor proveedor = new Proveedor();
        proveedor.setId(1);
        when(proveedorRepository.findById(1)).thenReturn(Optional.of(proveedor));

        String result = proveedorService.getProvider(solicitante, 1);

        assertNotNull(result);
        assertTrue(result.contains("ID del proveedor: 1"));
        verify(proveedorRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteProvider() {
        User solicitante = new User();
        solicitante.setNombre("admin");
        when(solicitante.puedeGestionarProveedores()).thenReturn(true);

        when(proveedorRepository.existsById(1)).thenReturn(true);

        String result = proveedorService.deleteProvider(solicitante, 1);

        assertEquals("Proveedor eliminado con éxito", result);
        verify(proveedorRepository, times(1)).deleteById(1);
    }

    @Test
    void testUpdateProvider() {
        User solicitante = new User();
        solicitante.setNombre("admin");
        when(solicitante.puedeGestionarProveedores()).thenReturn(true);

        Proveedor existingProveedor = new Proveedor();
        existingProveedor.setId(1);
        Proveedor updatedProveedor = new Proveedor();
        updatedProveedor.setNombre("Proveedor Actualizado");

        when(proveedorRepository.findById(1)).thenReturn(Optional.of(existingProveedor));
        when(proveedorRepository.save(existingProveedor)).thenReturn(updatedProveedor);

        String result = proveedorService.updateProvider(solicitante, 1, updatedProveedor);

        assertEquals("Proveedor actualizado con éxito", result);
        verify(proveedorRepository, times(1)).save(existingProveedor);
    }
}
