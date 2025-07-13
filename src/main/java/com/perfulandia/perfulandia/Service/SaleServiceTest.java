package com.perfulandia.perfulandia.Service;

import com.perfulandia.perfulandia.Model.Sale;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Repository.SaleRepository;
import com.perfulandia.perfulandia.Model.Sale;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Repository.SaleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SaleServiceTest {
    @Mock
    private SaleRepository saleRepository;

    @InjectMocks
    private SaleService saleService;

    @Test
    void testAddSaleConPermiso() {
        User solicitante = new User();
        solicitante.setNombre("admin");
        solicitante.setPuedeGestionarVentas(true);

        Sale sale = new Sale();
        sale.setDescripcion("Venta de prueba");

        when(saleRepository.save(sale)).thenReturn(sale);

        String result = saleService.addSale(solicitante, sale);

        assertEquals("Venta registrada con éxito", result);
        verify(saleRepository, times(1)).save(sale);
    }

    @Test
    void testAddSaleSinPermiso() {
        User solicitante = new User();
        solicitante.setNombre("usuario");
        solicitante.setPuedeGestionarVentas(false);

        Sale sale = new Sale();
        sale.setDescripcion("Venta de prueba");

        String result = saleService.addSale(solicitante, sale);

        assertEquals("No tienes permiso para registrar ventas", result);
        verify(saleRepository, never()).save(any(Sale.class));
    }
}
