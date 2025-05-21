package com.perfulandia.perfulandia.Service;
import com.perfulandia.perfulandia.Model.Product;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Repository.BranchRepository;
import org.springframework.stereotype.Service;

import com.perfulandia.perfulandia.Model.Branch;
import com.perfulandia.perfulandia.Repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BranchService {
    @Autowired
    private BranchRepository branchRepository;

        public String getBranchs(User solicitante) {
            if (!solicitante.puedeGestionarSucursales()) {
                return "No tienes permiso para ver sucursales";
            }
            String output = "";
            for (Branch branch : branchRepository.findAll()) {
                output += "ID Sucursal: " + branch.getId() + "\n";
                output += "Nombre: " + branch.getNombre() + "\n";
                output += "Direccion: " + branch.getDireccion() + "\n";
                output += "Horario de atencion: " + branch.getHorarioAtencion() + "\n";
            }
            if (output.isEmpty()) {
                return "No hay sucursales registradas";
            } else {
                return output;
            }
        }

        public String addBranch(User solicitante, Branch newBranch) {
            if (!solicitante.puedeGestionarSucursales()) {
                return "No tienes permiso para agregar sucursales";
            }
            branchRepository.save(newBranch);
            return "Sucursal agregada con éxito";
        }

        public String getBranch(User solicitante, int id) {
            if (!solicitante.puedeGestionarSucursales()) {
                return "No tienes permiso para ver sucursales";
            }
            String output = "";
            for (Branch branch : branchRepository.findAll()) {
                if (branch.getId() == id) {
                    output += "ID Sucursal: " + branch.getId() + "\n";
                    output += "Nombre: " + branch.getNombre() + "\n";
                    output += "Direccion: " + branch.getDireccion() + "\n";
                    output += "Horario de atencion: " + branch.getHorarioAtencion() + "\n";
                }
            }
            if (output.isEmpty()) {
                return "Sucursal no encontrada";
            } else {
                return output;
            }
        }

        public String deleteBranch(User solicitante, int id) {
            if (!solicitante.puedeGestionarSucursales()) {
                return "No tienes permiso para eliminar sucursales";
            }
            if (branchRepository.existsById(id)) {
                branchRepository.deleteById(id);
                return "Sucursal eliminada con éxito";
            } else {
                return "Sucursal no encontrada";
            }
        }

        public String updateBranch(User solicitante, int id, Branch newBranch) {
            if (!solicitante.puedeGestionarSucursales()) {
                return "No tienes permiso para actualizar sucursales";
            }
            if (branchRepository.existsById(id)) {
                for (Branch branch : branchRepository.findAll()) {
                    if (branch.getId() == id) {
                        branch.setNombre(newBranch.getNombre());
                        branch.setDireccion(newBranch.getDireccion());
                        branch.setHorarioAtencion(newBranch.getHorarioAtencion());
                        branchRepository.save(branch);
                    }
                }
                return "Sucursal actualizada con éxito";
            } else {
                return "Sucursal no encontrada";
            }
        }
    }

