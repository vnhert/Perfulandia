package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Model.Order;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    void testGetAllOrders() throws Exception {
        when(orderService.getOrders(any(User.class))).thenReturn("Lista de pedidos");

        mockMvc.perform(get("/pedidos")
                        .contentType("text/plain")
                        .content("nombre=admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("Lista de pedidos"));
    }

    @Test
    void testGetOrderById() throws Exception {
        when(orderService.getOrder(any(User.class), eq(1))).thenReturn("Detalles del pedido");

        mockMvc.perform(get("/pedidos/1")
                        .contentType("text/plain")
                        .content("nombre=admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("Detalles del pedido"));
    }

    @Test
    void testAddOrder() throws Exception {
        when(orderService.addOrder(any(User.class), any(Order.class))).thenReturn("Pedido agregado con éxito");

        mockMvc.perform(post("/pedidos")
                        .contentType("text/plain")
                        .content("solicitante=admin&estado=Pendiente"))
                .andExpect(status().isOk())
                .andExpect(content().string("Pedido agregado con éxito"));
    }

    @Test
    void testUpdateOrder() throws Exception {
        when(orderService.updateOrder(any(User.class), eq(1), any(Order.class)))
                .thenReturn("Pedido actualizado con éxito");

        mockMvc.perform(put("/pedidos/1")
                        .contentType("text/plain")
                        .content("solicitante=admin&estado=En proceso"))
                .andExpect(status().isOk())
                .andExpect(content().string("Pedido actualizado con éxito"));
    }

    @Test
    void testDeleteOrder() throws Exception {
        when(orderService.deleteOrder(any(User.class), eq(1))).thenReturn("Pedido eliminado con éxito");

        mockMvc.perform(delete("/pedidos/1")
                        .contentType("text/plain")
                        .content("nombre=admin"))
                .andExpect(status().isOk())
                .andExpect(content().string("Pedido eliminado con éxito"));
    }

    @Test
    void testUpdateStateOrder() throws Exception {
        when(orderService.updateStateOrder(any(User.class), eq(1), any(Order.class)))
                .thenReturn("Estado del Pedido actualizado con éxito");

        mockMvc.perform(put("/pedidos/1/estado")
                        .contentType("text/plain")
                        .content("solicitante=admin&estado=Completado"))
                .andExpect(status().isOk())
                .andExpect(content().string("Estado del Pedido actualizado con éxito"));
    }

    @Test
    void testGetOrdersByEstado() throws Exception {
        when(orderService.findByEstado("Pendiente")).thenReturn(List.of(new Order()));

        mockMvc.perform(get("/pedidos/estado/Pendiente")
                        .contentType("text/plain"))
                .andExpect(status().isOk());
    }
}