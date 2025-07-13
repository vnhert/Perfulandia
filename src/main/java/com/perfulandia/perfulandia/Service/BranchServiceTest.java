package com.perfulandia.perfulandia.Service;

import com.perfulandia.perfulandia.Model.Branch;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Repository.BranchRepository;
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
class BranchServiceTest {

    @Mock
    private BranchRepository branchRepository;

    @InjectMocks
    private BranchService branchService;

    @Test
    void testGetBranchesWithPermission() {
        User solicitante = new User();
        solicitante.setPuedeGestionarSucursales(true);

        when(branchRepository.findAll()).thenReturn(List.of(new Branch("Sucursal Centro", "Dirección 123")));

        List<Branch> result = branchService.getBranches(solicitante);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Sucursal Centro", result.get(0).getNombre());
        verify(branchRepository, times(1)).findAll();
    }

    @Test
    void testGetBranchesWithoutPermission() {
        User solicitante = new User();
        solicitante.setPuedeGestionarSucursales(false);

        assertThrows(UnauthorizedException.class, () -> branchService.getBranches(solicitante));
        verify(branchRepository, never()).findAll();
    }

    @Test
    void testAddBranchWithPermission() {
        User solicitante = new User();
        solicitante.setPuedeGestionarSucursales(true);
        Branch branch = new Branch("Sucursal Norte", "Dirección 456");

        when(branchRepository.save(any(Branch.class))).thenReturn(branch);

        Branch result = branchService.addBranch(solicitante, branch);

        assertNotNull(result);
        assertEquals("Sucursal Norte", result.getNombre());
        verify(branchRepository, times(1)).save(branch);
    }

    @Test
    void testAddBranchWithoutPermission() {
        User solicitante = new User();
        solicitante.setPuedeGestionarSucursales(false);
        Branch branch = new Branch("Sucursal Norte", "Dirección 456");

        assertThrows(UnauthorizedException.class, () -> branchService.addBranch(solicitante, branch));
        verify(branchRepository, never()).save(any(Branch.class));
    }

    @Test
    void testGetBranchWithPermission() {
        User solicitante = new User();
        solicitante.setPuedeGestionarSucursales(true);
        Branch branch = new Branch("Sucursal Centro", "Dirección 123");

        when(branchRepository.findById(1)).thenReturn(Optional.of(branch));

        Branch result = branchService.getBranch(solicitante, 1);

        assertNotNull(result);
        assertEquals("Sucursal Centro", result.getNombre());
        verify(branchRepository, times(1)).findById(1);
    }

    @Test
    void testGetBranchWithoutPermission() {
        User solicitante = new User();
        solicitante.setPuedeGestionarSucursales(false);

        assertThrows(UnauthorizedException.class, () -> branchService.getBranch(solicitante, 1));
        verify(branchRepository, never()).findById(anyInt());
    }

    @Test
    void testUpdateBranchWithPermission() {
        User solicitante = new User();
        solicitante.setPuedeGestionarSucursales(true);
        Branch existingBranch = new Branch("Sucursal Centro", "Dirección 123");
        Branch updatedBranch = new Branch("Sucursal Actualizada", "Nueva Dirección");

        when(branchRepository.findById(1)).thenReturn(Optional.of(existingBranch));
        when(branchRepository.save(any(Branch.class))).thenReturn(updatedBranch);

        Branch result = branchService.updateBranch(solicitante, 1, updatedBranch);

        assertNotNull(result);
        assertEquals("Sucursal Actualizada", result.getNombre());
        verify(branchRepository, times(1)).save(existingBranch);
    }

    @Test
    void testUpdateBranchWithoutPermission() {
        User solicitante = new User();
        solicitante.setPuedeGestionarSucursales(false);
        Branch updatedBranch = new Branch("Sucursal Actualizada", "Nueva Dirección");

        assertThrows(UnauthorizedException.class, () -> branchService.updateBranch(solicitante, 1, updatedBranch));
        verify(branchRepository, never()).findById(anyInt());
        verify(branchRepository, never()).save(any(Branch.class));
    }

    @Test
    void testDeleteBranchWithPermission() {
        User solicitante = new User();
        solicitante.setPuedeGestionarSucursales(true);

        when(branchRepository.findById(1)).thenReturn(Optional.of(new Branch("Sucursal Centro", "Dirección 123")));

        branchService.deleteBranch(solicitante, 1);

        verify(branchRepository, times(1)).delete(any(Branch.class));
    }

    @Test
    void testDeleteBranchWithoutPermission() {
        User solicitante = new User();
        solicitante.setPuedeGestionarSucursales(false);

        assertThrows(UnauthorizedException.class, () -> branchService.deleteBranch(solicitante, 1));
        verify(branchRepository, never()).findById(anyInt());
        verify(branchRepository, never()).delete(any(Branch.class));
    }
}