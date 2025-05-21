package com.perfulandia.perfulandia.Service;
import com.perfulandia.perfulandia.Model.Sale;
import com.perfulandia.perfulandia.Repository.SaleRepository;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;


    public String getSales(User solicitante) {
        if (!solicitante.puedeGestionarVentas()) {
            return "No tienes permiso para ver ventas";
        }
        String output = "";
        for (Sale sale : saleRepository.findAll()) {
            output += "ID de venta: " + sale.getId() + "\n";
            output += "Nombre del producto: " + sale.getProduct().getNombre() + "\n";
            output += "Cantidad: " + sale.getCantidad() + "\n";
            output += "Cliente: " + sale.getClient().getNombre() + "\n";
            output += "Fecha: " + sale.getFecha() + "\n\n";
        }

        if (output.isEmpty()) {
            return "No hay ventas registradas";
        } else {
            return output;
        }
    }

    public String addSale(User solicitante,Sale newSale) {
        if (!solicitante.puedeGestionarVentas()) {
            return "No tienes permiso para añadir ventas";
        }
        saleRepository.save(newSale);
        return "Venta agregada con éxito";
    }

    public String getSale(User solicitante,int id) {
        if (!solicitante.puedeGestionarVentas()) {
            return "No tienes permiso para ver ventas";
        }
        String output = "";
        for (Sale sale : saleRepository.findAll()) {
            if (sale.getId() == id) {
                output += "ID de venta: " + sale.getId() + "\n";
                output += "Producto: " + sale.getProduct().getNombre() + "\n";
                output += "Cantidad: " + sale.getCantidad() + "\n";
                output += "Cliente: " + sale.getClient().getNombre() + "\n";
                output += "Fecha: " + sale.getFecha();
            }
        }

        if (output.isEmpty()) {
            return "Venta no encontrada";
        } else {
            return output;
        }
    }

    public String deleteSale(User solicitante, int id) {
        if (!solicitante.puedeGestionarVentas()) {
            return "No tienes permiso para borrar ventas";
        }
        if (saleRepository.existsById(id)) {
            saleRepository.deleteById(id);
            return "Venta eliminada con éxito";
        } else {
            return "Venta no encontrada";
        }
    }

    public String updateSale(User solicitante,int id, Sale newSale) {
        if (!solicitante.puedeGestionarVentas()) {
            return "No tienes permiso para actualizar ventas";
        }
        if (saleRepository.existsById(id)) {
            for (Sale sale : saleRepository.findAll()) {
                if (sale.getId() == id) {
                    sale.setProduct(newSale.getProduct());
                    sale.setCantidad(newSale.getCantidad());
                    sale.setClient(newSale.getClient());
                    sale.setFecha(newSale.getFecha());
                    saleRepository.save(sale);
                }
            }
            return "Venta actualizada con éxito";
        } else {
            return "Venta no encontrada";
        }
    }

}
