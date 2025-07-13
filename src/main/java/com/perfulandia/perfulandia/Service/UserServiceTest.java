package com.perfulandia.perfulandia.Service;

import com.perfulandia.perfulandia.Model.*;
import com.perfulandia.perfulandia.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetUsers() {
        AdministradorSistema solicitante = new AdministradorSistema();
        solicitante.setNombre("admin");

        EmpleadoVentas user1 = new EmpleadoVentas();
        user1.setId(1);
        user1.setNombre("Juan");

        GerenteSucursal user2 = new GerenteSucursal();
        user2.setId(2);
        user2.setNombre("Maria");

        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        String result = userService.getUsers(solicitante);

        assertNotNull(result);
        assertTrue(result.contains("Juan"));
        assertTrue(result.contains("Maria"));
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void testGetUserById() {
        AdministradorSistema solicitante = new AdministradorSistema();
        solicitante.setNombre("admin");

        EmpleadoVentas user = new EmpleadoVentas();
        user.setId(1);
        user.setNombre("Juan");

        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        String result = userService.getUser(solicitante, 1);

        assertNotNull(result);
        assertTrue(result.contains("Juan"));
        verify(userRepository, times(1)).findById(1);
    }

    @Test
    void testAddUser() {
        AdministradorSistema solicitante = new AdministradorSistema();
        solicitante.setNombre("admin");

        EmpleadoVentas newUser = new EmpleadoVentas();
        newUser.setNombre("Pedro");

        when(userRepository.save(any(User.class))).thenReturn(newUser);

        String result = userService.addUser(solicitante, newUser);

        assertEquals("Usuario agregado con éxito", result);
        verify(userRepository, times(1)).save(newUser);
    }

    @Test
    void testUpdateUser() {
        AdministradorSistema solicitante = new AdministradorSistema();
        solicitante.setNombre("admin");

        EmpleadoVentas existingUser = new EmpleadoVentas();
        existingUser.setId(1);
        existingUser.setNombre("Juan");

        EmpleadoVentas updatedUser = new EmpleadoVentas();
        updatedUser.setNombre("Juan Actualizado");

        when(userRepository.findById(1)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        String result = userService.updateUser(solicitante, 1, updatedUser);

        assertEquals("Usuario actualizado con éxito", result);
        verify(userRepository, times(1)).save(existingUser);
    }

    @Test
    void testDeleteUser() {
        AdministradorSistema solicitante = new AdministradorSistema();
        solicitante.setNombre("admin");

        when(userRepository.existsById(1)).thenReturn(true);

        String result = userService.deleteUser(solicitante, 1);

        assertEquals("Usuario eliminado con éxito", result);
        verify(userRepository, times(1)).deleteById(1);
    }
}


