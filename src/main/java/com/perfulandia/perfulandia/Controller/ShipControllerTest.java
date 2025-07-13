package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Model.Ship;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Service.ShipService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(ShipController.class)
public class ShipControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShipService shipService;

    @Test
    void testCrearEnvio() throws Exception {
        Ship ship = new Ship();
        ship.setId(1);
        ship.setNombre("Envío de prueba");

        when(shipService.crearEnvio(any(), any(), any(), any(), any())).thenReturn(ship);

        mockMvc.perform(post("/envios/crear")
                        .contentType(APPLICATION_JSON)
                        .content("{\"solicitante\":{\"nombre\":\"admin\"},\"cliente\":1,\"productos\":[1,2],\"cantidades\":[10,5],\"cupon\":\"DESC10\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Envío de prueba"));
    }

    @Test
    void testGetAllShips() throws Exception {
        Ship ship = new Ship();
        ship.setId(1);
        ship.setNombre("Envío de prueba");

        when(shipService.getShips(any(User.class))).thenReturn(List.of(ship));

        mockMvc.perform(get("/envios")
                        .contentType(APPLICATION_JSON)
                        .content("{\"nombre\":\"admin\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.shipList[0].id").value(1))
                .andExpect(jsonPath("$._embedded.shipList[0].nombre").value("Envío de prueba"));
    }

    @Test
    void testGetShipById() throws Exception {
        Ship ship = new Ship();
        ship.setId(1);
        ship.setNombre("Envío de prueba");

        when(shipService.getShip(any(User.class), eq(1))).thenReturn(ship);

        mockMvc.perform(get("/envios/1")
                        .contentType(APPLICATION_JSON)
                        .content("{\"nombre\":\"admin\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Envío de prueba"));
    }

    @Test
    void testAddShip() throws Exception {
        Ship ship = new Ship();
        ship.setId(1);
        ship.setNombre("Envío A");

        when(shipService.addShip(any(User.class), any(Ship.class))).thenReturn(ship);

        mockMvc.perform(post("/envios")
                        .contentType(APPLICATION_JSON)
                        .content("{\"solicitante\":{\"nombre\":\"admin\"},\"newShip\":{\"nombre\":\"Envío A\"}}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Envío A"));
    }

    @Test
    void testUpdateShip() throws Exception {
        Ship ship = new Ship();
        ship.setId(1);
        ship.setNombre("Envío Actualizado");

        when(shipService.updateShip(any(User.class), eq(1), any(Ship.class))).thenReturn(ship);

        mockMvc.perform(put("/envios/1")
                        .contentType(APPLICATION_JSON)
                        .content("{\"solicitante\":{\"nombre\":\"admin\"},\"newShip\":{\"nombre\":\"Envío Actualizado\"}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Envío Actualizado"));
    }

    @Test
    void testDeleteShip() throws Exception {
        when(shipService.deleteShip(any(User.class), eq(1))).thenReturn("Envío eliminado con éxito");

        mockMvc.perform(delete("/envios/1")
                        .contentType(APPLICATION_JSON)
                        .content("{\"nombre\":\"admin\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Envío eliminado con éxito"));
    }
}