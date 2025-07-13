package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Model.Sale;
import com.perfulandia.perfulandia.Model.SaleActionRequest;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Service.SaleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.util.List;

@WebMvcTest(SaleController.class)
public class SaleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SaleService saleService;

    @Test
    void testGetAllSales() throws Exception {
        Sale sale = new Sale();
        sale.setId(1);
        sale.setDescripcion("Venta de prueba");

        when(saleService.getSales()).thenReturn(List.of(sale));

        mockMvc.perform(get("/ventas")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.saleList[0].id").value(1))
                .andExpect(jsonPath("$._embedded.saleList[0].descripcion").value("Venta de prueba"));
    }

    @Test
    void testGetSaleById() throws Exception {
        Sale sale = new Sale();
        sale.setId(1);
        sale.setDescripcion("Venta de prueba");

        when(saleService.getSale(1)).thenReturn(sale);

        mockMvc.perform(get("/ventas/1")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descripcion").value("Venta de prueba"));
    }

    @Test
    void testAddSale() throws Exception {
        Sale sale = new Sale();
        sale.setId(1);
        sale.setDescripcion("Venta de prueba");

        when(saleService.addSale(any(User.class), any(Sale.class))).thenReturn(sale);

        mockMvc.perform(post("/ventas")
                        .contentType(APPLICATION_JSON)
                        .content("{\"solicitante\":{\"nombre\":\"admin\"},\"sale\":{\"descripcion\":\"Venta de prueba\"}}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descripcion").value("Venta de prueba"));
    }

    @Test
    void testDeleteSale() throws Exception {
        when(saleService.deleteSale(any(User.class), eq(1))).thenReturn("Venta eliminada con éxito");

        mockMvc.perform(delete("/ventas/1")
                        .contentType(APPLICATION_JSON)
                        .content("{\"nombre\":\"admin\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Venta eliminada con éxito"));
    }

    @Test
    void testUpdateSale() throws Exception {
        Sale sale = new Sale();
        sale.setId(1);
        sale.setDescripcion("Venta actualizada");

        when(saleService.updateSale(any(User.class), eq(1), any(Sale.class))).thenReturn(sale);

        mockMvc.perform(put("/ventas/1")
                        .contentType(APPLICATION_JSON)
                        .content("{\"solicitante\":{\"nombre\":\"admin\"},\"sale\":{\"descripcion\":\"Venta actualizada\"}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.descripcion").value("Venta actualizada"));
    }

    @Test
    void testGetSalesByClient() throws Exception {
        Sale sale = new Sale();
        sale.setId(1);
        sale.setDescripcion("Venta de cliente");

        when(saleService.findByClientId(1)).thenReturn(List.of(sale));

        mockMvc.perform(get("/ventas/cliente/1")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.saleList[0].id").value(1))
                .andExpect(jsonPath("$._embedded.saleList[0].descripcion").value("Venta de cliente"));
    }
}