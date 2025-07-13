package com.perfulandia.perfulandia.Service;

import com.perfulandia.perfulandia.Model.Branch;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Repository.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    public List<Branch> getBranches(User solicitante) {
        if (!solicitante.puedeGestionarSucursales()) {
            throw new UnauthorizedException("No tienes permiso para ver sucursales");
        }
        return branchRepository.findAll();
    }

    public Branch getBranch(User solicitante, int id) {
        if (!solicitante.puedeGestionarSucursales()) {
            throw new UnauthorizedException("No tienes permiso para ver sucursales");
        }
        return branchRepository.findById(id)
                .orElseThrow(() -> new BranchNotFoundException("Sucursal no encontrada con ID: " + id));
    }

    public Branch addBranch(User solicitante, Branch branch) {
        if (!solicitante.puedeGestionarSucursales()) {
            throw new UnauthorizedException("No tienes permiso para agregar sucursales");
        }
        return branchRepository.save(branch);
    }

    public Branch updateBranch(User solicitante, int id, Branch updatedBranch) {
        if (!solicitante.puedeGestionarSucursales()) {
            throw new UnauthorizedException("No tienes permiso para actualizar sucursales");
        }
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new BranchNotFoundException("Sucursal no encontrada con ID: " + id));
        branch.setNombre(updatedBranch.getNombre());
        branch.setDireccion(updatedBranch.getDireccion());
        return branchRepository.save(branch);
    }

    public void deleteBranch(User solicitante, int id) {
        if (!solicitante.puedeGestionarSucursales()) {
            throw new UnauthorizedException("No tienes permiso para eliminar sucursales");
        }
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new BranchNotFoundException("Sucursal no encontrada con ID: " + id));
        branchRepository.delete(branch);
    }
}

