package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Model.Proveedor;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Service.ProveedorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProveedorController.class)
public class ProveedorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProveedorService proveedorService;

    @Test
    void testGetAllProviders() throws Exception {
        when(proveedorService.getProviders(any(User.class))).thenReturn("Lista de proveedores");

        mockMvc.perform(get("/proveedores")
                        .contentType("application/x-www-form-urlencoded")
                        .content("nombre=admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("Lista de proveedores"));
    }

    @Test
    void testGetProviderById() throws Exception {
        when(proveedorService.getProvider(any(User.class), eq(1))).thenReturn("Detalles del proveedor");

        mockMvc.perform(get("/proveedores/1")
                        .contentType("application/x-www-form-urlencoded")
                        .content("nombre=admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("Detalles del proveedor"));
    }

    @Test
    void testAddProvider() throws Exception {
        when(proveedorService.addProvider(any(User.class), any(Proveedor.class))).thenReturn("Proveedor agregado con éxito");

        mockMvc.perform(post("/proveedores")
                        .contentType("application/x-www-form-urlencoded")
                        .content("solicitante=admin&nombreProveedor=Proveedor+Nuevo"))
                .andExpect(status().isOk())
                .andExpect(content().string("Proveedor agregado con éxito"));
    }

    @Test
    void testUpdateProvider() throws Exception {
        when(proveedorService.updateProvider(any(User.class), eq(1), any(Proveedor.class))).thenReturn("Proveedor actualizado con éxito");

        mockMvc.perform(put("/proveedores/1")
                        .contentType("application/x-www-form-urlencoded")
                        .content("solicitante=admin&nombreProveedor=Proveedor+Actualizado"))
                .andExpect(status().isOk())
                .andExpect(content().string("Proveedor actualizado con éxito"));
    }

    @Test
    void testDeleteProvider() throws Exception {
        when(proveedorService.deleteProvider(any(User.class), eq(1))).thenReturn("Proveedor eliminado con éxito");

        mockMvc.perform(delete("/proveedores/1")
                        .contentType("application/x-www-form-urlencoded")
                        .content("nombre=admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("Proveedor eliminado con éxito"));
    }
}