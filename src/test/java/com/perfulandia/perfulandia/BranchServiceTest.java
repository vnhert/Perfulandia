package com.perfulandia.perfulandia;

import com.perfulandia.perfulandia.Model.Branch;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Repository.BranchRepository;
import com.perfulandia.perfulandia.Service.BranchService;
import com.perfulandia.perfulandia.Service.BranchNotFoundException;
import com.perfulandia.perfulandia.Service.UnauthorizedException;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
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
    void testGetBranches_UserHasPermission_ReturnsAllBranchesSuccessfully() {
        // Arrange
        User solicitante = mock(User.class); // CORRECTED: Use mock()
        when(solicitante.puedeGestionarSucursales()).thenReturn(true); // CORRECTED: Use when().thenReturn()

        List<Branch> expectedBranches = Arrays.asList(
                new Branch(1, "Sucursal Central", "Calle Falsa 123", "9-18"),
                new Branch(2, "Sucursal Sur", "Avenida Siempre Viva 456", "10-19")
        );
        when(branchRepository.findAll()).thenReturn(expectedBranches);

        // Act
        List<Branch> actualBranches = branchService.getBranches(solicitante);

        // Assert
        assertNotNull(actualBranches);
        assertEquals(expectedBranches.size(), actualBranches.size());
        assertEquals(expectedBranches.get(0).getNombre(), actualBranches.get(0).getNombre());
        assertEquals(expectedBranches.get(1).getNombre(), actualBranches.get(1).getNombre());

        verify(branchRepository, times(1)).findAll();
        verify(solicitante, atLeastOnce()).puedeGestionarSucursales();
    }

    @Test
    void testAddBranch_UserHasPermission_ReturnsSavedBranch() {
        // Arrange
        User solicitante = mock(User.class); // CORRECTED
        when(solicitante.puedeGestionarSucursales()).thenReturn(true); // CORRECTED

        Branch branchToSave = new Branch(0, "Sucursal Oeste", "Calle Ficticia 789", "11-21");
        Branch savedBranch = new Branch(3, "Sucursal Oeste", "Calle Ficticia 789", "11-21");
        when(branchRepository.save(any(Branch.class))).thenReturn(savedBranch);

        // Act
        Branch result = branchService.addBranch(solicitante, branchToSave);

        // Assert
        assertNotNull(result);
        assertEquals(savedBranch.getId(), result.getId());
        assertEquals(savedBranch.getNombre(), result.getNombre());
        verify(branchRepository, times(1)).save(branchToSave);
        verify(solicitante, atLeastOnce()).puedeGestionarSucursales();
    }

    @Test
    void testOperation_UserLacksPermission_ThrowsUnauthorizedException() {
        // Arrange
        User solicitante = mock(User.class); // CORRECTED
        when(solicitante.puedeGestionarSucursales()).thenReturn(false); // CORRECTED

        // Act & Assert for getBranches
        assertThrows(UnauthorizedException.class, () -> branchService.getBranches(solicitante));
        verify(branchRepository, never()).findAll();

        // Act & Assert for addBranch
        Branch dummyBranch = new Branch(0, "Dummy", "Dummy", "0-0");
        assertThrows(UnauthorizedException.class, () -> branchService.addBranch(solicitante, dummyBranch));
        verify(branchRepository, never()).save(any(Branch.class));
    }

    @Test
    void testUpdateBranch_BranchNotFound_ThrowsBranchNotFoundException() {
        // Arrange
        User solicitante = mock(User.class); // CORRECTED
        when(solicitante.puedeGestionarSucursales()).thenReturn(true); // CORRECTED

        int nonExistentBranchId = 999;
        Branch updatedBranchData = new Branch(0, "Updated Name", "Updated Address", "12-22");

        when(branchRepository.findById(nonExistentBranchId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BranchNotFoundException.class,
                () -> branchService.updateBranch(solicitante, nonExistentBranchId, updatedBranchData));

        verify(branchRepository, times(1)).findById(nonExistentBranchId);
        verify(branchRepository, never()).save(any(Branch.class));
        verify(solicitante, atLeastOnce()).puedeGestionarSucursales();
    }

    @Test
    void testGetBranchWithPermission_ReturnsBranchSuccessfully() {
        // Arrange
        User solicitante = mock(User.class); // CORRECTED
        when(solicitante.puedeGestionarSucursales()).thenReturn(true); // CORRECTED

        int branchId = 1;
        Branch expectedBranch = new Branch(branchId, "Sucursal Test", "Dir Test", "Horario Test");
        when(branchRepository.findById(branchId)).thenReturn(Optional.of(expectedBranch));

        // Act
        Branch actualBranch = branchService.getBranch(solicitante, branchId);

        // Assert
        assertNotNull(actualBranch);
        assertEquals(expectedBranch.getNombre(), actualBranch.getNombre());
        verify(branchRepository, times(1)).findById(branchId);
        verify(solicitante, atLeastOnce()).puedeGestionarSucursales();
    }

    @Test
    void testGetBranchNotFound_ThrowsBranchNotFoundException() {
        // Arrange
        User solicitante = mock(User.class);
        when(solicitante.puedeGestionarSucursales()).thenReturn(true);

        int nonExistentId = 999;
        when(branchRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(BranchNotFoundException.class, () -> branchService.getBranch(solicitante, nonExistentId));
        verify(branchRepository, times(1)).findById(nonExistentId);
        verify(solicitante, atLeastOnce()).puedeGestionarSucursales();
    }
}