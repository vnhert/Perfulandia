package com.perfulandia.perfulandia;
import com.perfulandia.perfulandia.Model.Order;
import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Repository.OrderRepository;
import com.perfulandia.perfulandia.Service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void testGetOrders() {
        User solicitante = new User();
        solicitante.setNombre("admin");
        when(solicitante.puedeGestionarPedidos()).thenReturn(true);

        when(orderRepository.findAll()).thenReturn(List.of(new Order()));

        String result = orderService.getOrders(solicitante);

        assertNotNull(result);
        assertTrue(result.contains("ID de pedido"));
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void testAddOrder() {
        User solicitante = new User();
        solicitante.setNombre("admin");
        when(solicitante.puedeGestionarPedidos()).thenReturn(true);

        Order order = new Order();
        when(orderRepository.save(order)).thenReturn(order);

        String result = orderService.addOrder(solicitante, order);

        assertEquals("Pedido agregado con éxito", result);
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testGetOrder() {
        User solicitante = new User();
        solicitante.setNombre("admin");
        when(solicitante.puedeGestionarPedidos()).thenReturn(true);

        Order order = new Order();
        order.setId(1);
        when(orderRepository.findById(1)).thenReturn(Optional.of(order));

        String result = orderService.getOrder(solicitante, 1);

        assertNotNull(result);
        assertTrue(result.contains("ID de pedido: 1"));
        verify(orderRepository, times(1)).findById(1);
    }

    @Test
    void testDeleteOrder() {
        User solicitante = new User();
        solicitante.setNombre("admin");
        when(solicitante.puedeGestionarPedidos()).thenReturn(true);

        when(orderRepository.existsById(1)).thenReturn(true);

        String result = orderService.deleteOrder(solicitante, 1);

        assertEquals("Pedido eliminado con éxito", result);
        verify(orderRepository, times(1)).deleteById(1);
    }

    @Test
    void testUpdateOrder() {
        User solicitante = new User();
        solicitante.setNombre("admin");
        when(solicitante.puedeGestionarPedidos()).thenReturn(true);

        Order existingOrder = new Order();
        existingOrder.setId(1);
        Order updatedOrder = new Order();
        updatedOrder.setEstado("En proceso");

        when(orderRepository.findById(1)).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(existingOrder)).thenReturn(updatedOrder);

        String result = orderService.updateOrder(solicitante, 1, updatedOrder);

        assertEquals("Pedido actualizado con éxito", result);
        verify(orderRepository, times(1)).save(existingOrder);
    }

    @Test
    void testUpdateStateOrder() {
        User solicitante = new User();
        solicitante.setNombre("admin");
        when(solicitante.puedeActualizarPedido()).thenReturn(true);

        Order existingOrder = new Order();
        existingOrder.setId(1);
        Order updatedOrder = new Order();
        updatedOrder.setEstado("Completado");

        when(orderRepository.findById(1)).thenReturn(Optional.of(existingOrder));
        when(orderRepository.save(existingOrder)).thenReturn(updatedOrder);

        String result = orderService.updateStateOrder(solicitante, 1, updatedOrder);

        assertEquals("Estado del Pedido actualizado con éxito", result);
        verify(orderRepository, times(1)).save(existingOrder);
    }

    @Test
    void testFindByEstado() {
        when(orderRepository.findByEstado("Pendiente")).thenReturn(List.of(new Order()));

        List<Order> result = orderService.findByEstado("Pendiente");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(orderRepository, times(1)).findByEstado("Pendiente");
    }
}
