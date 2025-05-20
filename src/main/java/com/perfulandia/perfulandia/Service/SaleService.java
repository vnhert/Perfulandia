package com.perfulandia.perfulandia.Service;
import com.perfulandia.perfulandia.Model.Sale;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleService {
    private List<Sale> sales = new ArrayList<>();

    public String getSales() {
        if (sales.isEmpty()) {
            return "No hay ventas registradas";
        }

        StringBuilder output = new StringBuilder();
        for (Sale sale : sales) {
            output.append("ID de venta: ").append(sale.getId()).append("\n");
            output.append("Nombre del producto: ").append(sale.getProduct().getNombre()).append("\n");
            output.append("Cantidad: ").append(sale.getCantidad()).append("\n");
            output.append("Fecha: ").append(sale.getFecha()).append("\n\n");
        }

        return output.toString();
    }

    public String addSale(Sale newSale) {
        sales.add(newSale);
        return "Venta agregado con éxito";
    }

    public String getSale(int id, String fecha) {
        String output = "";

        for (Sale sale : sales) {
            if (sale.getProduct().getId() == id && sale.getFecha().equals(fecha)) {
                output += "ID de venta: " + sale.getId() + "\n";
                output += "Nombre: " + sale.getProduct().getNombre() + "\n";
                output += "Cantidad: " + sale.getCantidad() + "\n";
                output += "Cliente: " + sale.getClient() + "\n";
                output += "Fecha: " + sale.getFecha();
            }
        }

        if (output.isEmpty()) {
            return "Venta no encontrada";
        } else {
            return output;
        }
    }

    public String deleteSale(int id, String fecha) {
        for (Sale sale : sales) {
            if (sale.getId() == id && sale.getFecha().equals(fecha)) {
                sales.remove(sale);
                return "Venta eliminada con éxito";
            }
        }
        return "Venta no encontrada";
    }

    public String updateSale(Sale updatedSale) {
        int index = -1;

        for (int i = 0; i < sales.size(); i++) {
            Sale o = sales.get(i);
            if (o.getProduct().getId() == updatedSale.getProduct().getId() &&
                    o.getFecha().equals(updatedSale.getFecha())) {
                index = i;
            }
        }

        if (index == -1) {
            return "Venta no encontrada";
        } else {
            sales.set(index, updatedSale);
            return "Venta actualizada con éxito";
        }
    }
}
