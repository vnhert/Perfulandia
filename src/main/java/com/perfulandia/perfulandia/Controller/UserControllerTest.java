package com.perfulandia.perfulandia.Controller;

import com.perfulandia.perfulandia.Model.User;
import com.perfulandia.perfulandia.Service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void testGetAllUsers() throws Exception {
        User user = new User();
        user.setId(1);
        user.setNombre("Admin");
        user.setCorreo("admin@mail.com");

        when(userService.getUsers(any(User.class))).thenReturn(List.of(user));

        mockMvc.perform(get("/usuarios")
                        .contentType(APPLICATION_JSON)
                        .content("{\"nombre\":\"admin\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.userList[0].id").value(1))
                .andExpect(jsonPath("$._embedded.userList[0].nombre").value("Admin"))
                .andExpect(jsonPath("$._embedded.userList[0].correo").value("admin@mail.com"));
    }

    @Test
    void testGetUserById() throws Exception {
        User user = new User();
        user.setId(1);
        user.setNombre("Pedro");
        user.setCorreo("pedro@mail.com");

        when(userService.getUser(any(User.class), eq(1))).thenReturn(user);

        mockMvc.perform(get("/usuarios/1")
                        .contentType(APPLICATION_JSON)
                        .content("{\"nombre\":\"admin\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Pedro"))
                .andExpect(jsonPath("$.correo").value("pedro@mail.com"));
    }

    @Test
    void testAddUser() throws Exception {
        User user = new User();
        user.setId(1);
        user.setNombre("Nuevo Usuario");
        user.setCorreo("nuevo@mail.com");

        when(userService.addUser(any(User.class), any(User.class))).thenReturn(user);

        mockMvc.perform(post("/usuarios")
                        .contentType(APPLICATION_JSON)
                        .content("{\"solicitante\":{\"nombre\":\"admin\"},\"user\":{\"nombre\":\"Nuevo Usuario\",\"correo\":\"nuevo@mail.com\"}}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Nuevo Usuario"))
                .andExpect(jsonPath("$.correo").value("nuevo@mail.com"));
    }

    @Test
    void testDeleteUser() throws Exception {
        when(userService.deleteUser(any(User.class), eq(1))).thenReturn("Usuario eliminado con éxito");

        mockMvc.perform(delete("/usuarios/1")
                        .param("solicitanteId", "1")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuario eliminado con éxito"));
    }

    @Test
    void testUpdateUser() throws Exception {
        User user = new User();
        user.setId(1);
        user.setNombre("Usuario Actualizado");
        user.setCorreo("actualizado@mail.com");

        when(userService.updateUser(any(User.class), eq(1), any(User.class))).thenReturn(user);

        mockMvc.perform(put("/usuarios/1")
                        .contentType(APPLICATION_JSON)
                        .content("{\"solicitante\":{\"nombre\":\"admin\"},\"user\":{\"nombre\":\"Usuario Actualizado\",\"correo\":\"actualizado@mail.com\"}}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nombre").value("Usuario Actualizado"))
                .andExpect(jsonPath("$.correo").value("actualizado@mail.com"));
    }

    @Test
    void testGetUsersWithoutPermission() throws Exception {
        when(userService.getUsers(any(User.class))).thenThrow(new RuntimeException("No tienes permiso para ver usuarios"));

        mockMvc.perform(get("/usuarios")
                        .contentType(APPLICATION_JSON)
                        .content("{\"nombre\":\"usuarioSinPermiso\"}"))
                .andExpect(status().isForbidden())
                .andExpect(content().string("No tienes permiso para ver usuarios"));
    }

    @Test
    void testGetUserByIdNotFound() throws Exception {
        when(userService.getUser(any(User.class), eq(99))).thenThrow(new RuntimeException("Usuario no encontrado"));

        mockMvc.perform(get("/usuarios/99")
                        .contentType(APPLICATION_JSON)
                        .content("{\"nombre\":\"admin\"}"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Usuario no encontrado"));
    }
}
