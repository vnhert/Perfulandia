package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Model.Branch;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Service.BranchService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BranchController.class)
class BranchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BranchService branchService;

    @Test
    void testGetAllBranches() throws Exception {
        when(branchService.getBranchs(any(User.class))).thenReturn("Lista de sucursales");

        mockMvc.perform(get("/sucursales")
                        .contentType("text/plain")
                        .content("nombre=admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("Lista de sucursales"));
    }

    @Test
    void testAddBranch() throws Exception {
        when(branchService.addBranch(any(User.class), any(Branch.class))).thenReturn("Sucursal agregada");

        mockMvc.perform(post("/sucursales")
                        .contentType("text/plain")
                        .content("solicitante=admin&nombreSucursal=Sucursal Centro"))
                .andExpect(status().isOk())
                .andExpect(content().string("Sucursal agregada"));
    }

    @Test
    void testDeleteBranch() throws Exception {
        when(branchService.deleteBranch(any(), eq(1))).thenReturn("Sucursal eliminada con éxito");

        mockMvc.perform(delete("/sucursales/1")
                        .contentType("text/plain")
                        .content("nombre=admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("Sucursal eliminada con éxito"));
    }

    @Test
    void testGetBranchById() throws Exception {
        when(branchService.getBranch(any(User.class), eq(1))).thenReturn("Detalles de la sucursal");

        mockMvc.perform(get("/sucursales/1")
                        .contentType("text/plain")
                        .content("nombre=admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("Detalles de la sucursal"));
    }

    @Test
    void testUpdateBranch() throws Exception {
        when(branchService.updateBranch(any(User.class), eq(1), any(Branch.class)))
                .thenReturn("Sucursal actualizada con éxito");

        mockMvc.perform(put("/sucursales/1")
                        .contentType("text/plain")
                        .content("solicitante=admin&nombreSucursal=Sucursal Actualizada"))
                .andExpect(status().isOk())
                .andExpect(content().string("Sucursal actualizada con éxito"));
    }
}
