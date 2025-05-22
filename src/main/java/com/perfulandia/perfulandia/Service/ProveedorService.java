package com.perfulandia.perfulandia.Service;



import com.perfulandia.perfulandia.Model.Proveedor;

import com.perfulandia.perfulandia.Repository.ProveedorRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.perfulandia.perfulandia.Model.User;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public String getProviders(User solicitante) {
        if (!solicitante.puedeGestionarProveedores()) {
            return "No tienes permiso para ver proveedores";
        }
        String output = "";
        for (Proveedor proveedor : proveedorRepository.findAll()) {
            output += "ID Proveedor: " + proveedor.getId() + "\n";
            output += "Nombre: " + proveedor.getNombre() + "\n";
            output += "Telefono: " + proveedor.getTelefono() + "\n";
            output += "Direccion: " + proveedor.getDireccion() + "\n";
            output += "Contacto: " + proveedor.getContacto() + "\n";


        }

        if (output.isEmpty()) {
            return "No hay proveedores registrados";
        } else {
            return output;
        }
    }

    public String addProvider(User solicitante,Proveedor newProvider) {
        if (!solicitante.puedeGestionarProveedores()) {
            return "No tienes permiso para agregar proveedores";
        }
        proveedorRepository.save(newProvider);
        return "Proveedor agregado con éxito";
    }

    public String getProvider(User solicitante,int id) {
        if (!solicitante.puedeGestionarProveedores()) {
            return "No tienes permiso para ver proveedores";
        }
        String output = "";
        for (Proveedor proveedor : proveedorRepository.findAll()) {
            if (proveedor.getId() == id) {
                output += "ID Proveedor: " + proveedor.getId() + "\n";
                output += "Nombre: " + proveedor.getNombre() + "\n";
                output += "Telefono: " + proveedor.getTelefono() + "\n";
                output += "Direccion: " + proveedor.getDireccion() + "\n";
                output += "Contacto: " + proveedor.getContacto() + "\n";

            }
        }

        if (output.isEmpty()) {
            return "Proveedor no encontrado";
        } else {
            return output;
        }
    }

    public String deleteProvider(User solicitante,int id) {
        if (!solicitante.puedeGestionarProveedores()) {
            return "No tienes permiso para eliminar proveedores";
        }
        if (proveedorRepository.existsById(id)) {
            proveedorRepository.deleteById(id);
            return "Proveedor eliminado con éxito";
        } else {
            return "Proveedor no encontrado";
        }
    }

    public String updateProvider(User solicitante,int id, Proveedor newProvider) {
        if (!solicitante.puedeGestionarProveedores()) {
            return "No tienes permiso para actualizar proveedores";
        }
        if (proveedorRepository.existsById(id)) {
            for (Proveedor proveedor : proveedorRepository.findAll()) {
                if (proveedor.getId() == id) {
                    proveedor.setNombre(newProvider.getNombre());
                    proveedor.setTelefono(newProvider.getTelefono());
                    proveedor.setDireccion(newProvider.getDireccion());
                    proveedor.setContacto(newProvider.getContacto());

                }
            }
            return "Proveedor actualizado con éxito";
        } else {
            return "Proveedor no encontrado";
        }
    }
}
