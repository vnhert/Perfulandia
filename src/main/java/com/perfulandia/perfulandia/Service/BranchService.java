package com.perfulandia.perfulandia.Service;
import com.perfulandia.perfulandia.Model.Product;
import com.perfulandia.perfulandia.Repository.BranchRepository;
import org.springframework.stereotype.Service;

import com.perfulandia.perfulandia.Model.Branch;
import com.perfulandia.perfulandia.Repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class BranchService {
    @Autowired
    private BranchRepository branchRepository;


    public String getBranchs() {
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

    public String addBranch(Branch newBranch) {
        branchRepository.save(newBranch);
        return "Sucursal agregada con éxito";
    }
    public String getBranch(int id) {
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
            return "Producto no encontrado";
        } else {
            return output;
        }
    }
    public String deleteBranch(int id) {
        if (branchRepository.existsById(id)) {
            branchRepository.deleteById(id);
            return "Sucursal eliminada con éxito";
        } else {
            return "Sucursal no encontrada";
        }
    }
    public String updateBranch(int id, Branch newBranch) {
        if (branchRepository.existsById(id)) {
            for (Branch branch : branchRepository.findAll()) {
                if (branch.getId() == id) {
                    branch.setNombre(newBranch.getNombre());
                    branch.setDireccion(newBranch.getDireccion());
                    branch.setHorarioAtencion(newBranch.getHorarioAtencion());
                    branchRepository.save(branch);
                }
            }
            return "Producto actualizado con éxito";
        } else {
            return "Producto no encontrado";
        }
    }

}
