package com.perfulandia.perfulandia.Service;

import org.springframework.stereotype.Service;

import com.perfulandia.perfulandia.Model.Product;
import com.perfulandia.perfulandia.Repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import com.perfulandia.perfulandia.Model.User;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public String getProducts(User solicitante) {
        if (!solicitante.puedeGestionarProductos()) {
            return "No tienes permiso para ver productos";
        }
        String output = "";
        for (Product product : productRepository.findAll()) {
            output += "ID Producto: " + product.getId() + "\n";
            output += "Nombre: " + product.getNombre() + "\n";
            output += "Descripcion: " + product.getDescripcion() + "\n";
            output += "Precio: " + product.getPrecio() + "\n";
            output += "Stock: " + product.getStock() + "\n\n";
        }

        if (output.isEmpty()) {
            return "No hay productos registrados";
        } else {
            return output;
        }
    }
    public String verProducts(User solicitante) {
        if (!solicitante.puedeVerProductos()) {
            return "No tienes permiso para ver productos";
        }
        String output = "";
        for (Product product : productRepository.findAll()) {
            output += "ID Producto: " + product.getId() + "\n";
            output += "Nombre: " + product.getNombre() + "\n";
            output += "Descripcion: " + product.getDescripcion() + "\n";
            output += "Precio: " + product.getPrecio() + "\n";
            output += "Stock: " + product.getStock() + "\n\n";
        }

        if (output.isEmpty()) {
            return "No hay productos registrados";
        } else {
            return output;
        }
    }

    public String addProduct(User solicitante,Product newProduct) {
        if (!solicitante.puedeGestionarProductos()) {
            return "No tienes permiso para agregar productos";
        }
        productRepository.save(newProduct);
        return "Producto agregado con éxito";
    }

    public String getProduct(User solicitante,int id) {
        if (!solicitante.puedeGestionarProductos()) {
            return "No tienes permiso para ver productos";
        }
        String output = "";
        for (Product product : productRepository.findAll()) {
            if (product.getId() == id) {
                output += "ID Producto: " + product.getId() + "\n";
                output += "Nombre: " + product.getNombre() + "\n";
                output += "Descripcion: " + product.getDescripcion() + "\n";
                output += "Precio: " + product.getPrecio() + "\n";
                output += "Stock: " + product.getStock() + "\n";
            }
        }

        if (output.isEmpty()) {
            return "Producto no encontrado";
        } else {
            return output;
        }
    }

    public String deleteProduct(User solicitante,int id) {
        if (!solicitante.puedeGestionarProductos()) {
            return "No tienes permiso para eliminar productos";
        }
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return "Producto eliminado con éxito";
        } else {
            return "Producto no encontrado";
        }
    }

    public String updateProduct(User solicitante,int id, Product newProduct) {
        if (!solicitante.puedeGestionarProductos()) {
            return "No tienes permiso para actualizar productos";
        }
        if (productRepository.existsById(id)) {
            for (Product product : productRepository.findAll()) {
                if (product.getId() == id) {
                    product.setNombre(newProduct.getNombre());
                    product.setDescripcion(newProduct.getDescripcion());
                    product.setPrecio(newProduct.getPrecio());
                    product.setStock(newProduct.getStock());
                    product.setCategoria(newProduct.getCategoria());
                    productRepository.save(product);
                }
            }
            return "Producto actualizado con éxito";
        } else {
            return "Producto no encontrado";
        }
    }
}
